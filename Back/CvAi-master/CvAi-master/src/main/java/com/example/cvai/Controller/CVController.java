package com.example.cvai.Controller;
import com.example.cvai.Entity.CV;
import com.example.cvai.Entity.CVResponse;
import com.example.cvai.Entity.RankedCV;
import com.example.cvai.Repository.CVRepository;
import com.example.cvai.service.CVProcessingService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cv")
public class CVController {

    @Autowired
    private CVRepository cvRepository;

    @Autowired
    private CVProcessingService cvProcessingService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitCV(@RequestParam("name") String name,
                                           @RequestParam("email") String email,
                                           @RequestParam("file") MultipartFile file) {
        try {
            CV cv = new CV();
            cv.setName(name);
            cv.setEmail(email);
            cv.setFileData(file.getBytes());
            cvRepository.save(cv);
            return ResponseEntity.ok("CV submitted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to submit CV");
        }
    }

    @PostMapping("/submitMultiple")
    public ResponseEntity<List<CVResponse>> submitMultipleCVs(
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam("name") String name,
            @RequestParam("email") String email) {
        try {
            List<CVResponse> responses = new ArrayList<>();
            for (MultipartFile file : files) {
                CV cv = new CV();
                cv.setName(name);
                cv.setEmail(email);
                cv.setFileData(file.getBytes());
                CV savedCV = cvRepository.save(cv);

                CVResponse response = new CVResponse(savedCV.getId(), savedCV.getName(), savedCV.getEmail());
                responses.add(response);
            }
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @GetMapping("/rankAll")
    public ResponseEntity<List<RankedCV>> rankAllCVs() {
        try {
            List<CV> allCVs = cvRepository.findAll();
            List<MultipartFile> files = new ArrayList<>();

            for (CV cv : allCVs) {
                MultipartFile multipartFile = new MockMultipartFile("file", cv.getName(), "application/pdf", cv.getFileData());
                files.add(multipartFile);
            }

            List<RankedCV> rankedCVs = cvProcessingService.processAndRankCVs(files, allCVs);
            rankedCVs.sort((cv1, cv2) -> {
                int compare = Integer.compare(cv2.getExperienceDuration(), cv1.getExperienceDuration());
                if (compare == 0) {
                    return Double.compare(cv2.getScore(), cv1.getScore());
                }
                return compare;
            });

            rankAndPrintCVs(rankedCVs);

            return ResponseEntity.ok(rankedCVs);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    private void rankAndPrintCVs(List<RankedCV> rankedCVs) {
        System.out.println("Ranked CVs:");
        int rank = 1;
        for (RankedCV rankedCV : rankedCVs) {
            System.out.println("Rank " + rank + ": " + rankedCV.getCvText() +
                    " - Name: " + rankedCV.getName() +
                    " - Email: " + rankedCV.getEmail() +
                    " - Keyword count: " + rankedCV.getKeywordCount() +
                    " - Experience duration (months): " + rankedCV.getExperienceDuration() +
                    " - Score: " + rankedCV.getScore());
            rank++;
        }
    }
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadCV(@PathVariable Long id) {
        try {
            Optional<CV> optionalCV = cvRepository.findById(id);
            if (optionalCV.isPresent()) {
                CV cv = optionalCV.get();
                byte[] pdfData = convertToPDF(cv.getFileData(), cv.getName());

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", cv.getName() + ".pdf");

                return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
            } else {
                // If CV with the given ID is not found, return a 404 Not Found response
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    // The existing convertToPDF method ...
    private byte[] convertToPDF(byte[] fileData, String fileName) throws IOException {
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);

            // Use Helvetica font
            PDFont font = PDType1Font.HELVETICA;

            try (PDPageContentStream content = new PDPageContentStream(doc, page)) {
                content.beginText();
                content.moveTextPositionByAmount(10, 700);
                content.setFont(font, 12);
                content.drawString("start text");
                content.newLineAtOffset(0, -15);
                content.drawString("text in new line");

                // Add content from CV
                String cvText = extractTextFromCV(fileData); // Assuming you have a method to extract text from CV
                content.newLineAtOffset(0, -15); // Adjust as needed
                content.drawString(cvText); // Add the CV text
                content.endText();
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            doc.save(outputStream);
            return outputStream.toByteArray();
        }
    }
    private String extractTextFromCV(byte[] fileData) throws IOException {
        try (PDDocument document = PDDocument.load(fileData)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }
    @PostMapping("/accept/{id}")
    public ResponseEntity<String> acceptCV(@PathVariable String id) {
        try {
            Long cvId = Long.parseLong(id);
            if (cvId <= 0) {
                return ResponseEntity.badRequest().body("Invalid CV ID");
            }

            Optional<CV> optionalCV = cvRepository.findById(cvId);
            if (optionalCV.isPresent()) {
                CV cv = optionalCV.get();
                cv.setStatus("accepted");
                cvRepository.save(cv);
                return ResponseEntity.ok("CV accepted successfully!");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid CV ID");
        } catch (DataAccessException e) {
            // Log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to accept CV");
        }
    }

    @PostMapping("/reject/{id}")
    public ResponseEntity<String> rejectCV(@PathVariable String id) {
        try {
            Long cvId = Long.parseLong(id);
            if (cvId <= 0) {
                return ResponseEntity.badRequest().body("Invalid CV ID");
            }

            Optional<CV> optionalCV = cvRepository.findById(cvId);
            if (optionalCV.isPresent()) {
                CV cv = optionalCV.get();
                cv.setStatus("rejected");
                cvRepository.save(cv);
                return ResponseEntity.ok("CV rejected successfully!");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid CV ID");
        } catch (DataAccessException e) {
            // Log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to reject CV");
        }
    }

}