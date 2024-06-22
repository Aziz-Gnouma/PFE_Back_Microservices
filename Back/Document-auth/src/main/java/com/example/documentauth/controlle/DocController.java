package com.example.documentauth.controlle;


import com.example.documentauth.Service.DocService;
import com.example.documentauth.entity.Doc;
import com.example.documentauth.entity.QRCodeGenerator;
import com.example.documentauth.entity.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.example.documentauth.entity.DocumentUtils.decodeDetails;
import static com.example.documentauth.entity.DocumentUtils.generateDetailsString;

@RestController
@RequestMapping("/api/docs")
public class DocController {

    @Autowired
    private DocService docService;
    @Autowired
    private QRCodeGenerator qrCodeGenerator;

    @PostMapping
    public Doc addDoc(@RequestBody Doc doc) {
        doc.setId(UUID.randomUUID().getMostSignificantBits()); // generate unique ID
        doc.setIssueDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        doc.setExpirationDate(Date.from(LocalDate.now().plusYears(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        doc.setDocumentStatus("active");
        return docService.saveDoc(doc);
    }
    @GetMapping
    public List<Doc> getAllDocs() {
        return docService.getAllDocs();
    }
    // Méthode pour générer le code QR dans le contrôleur
    @PostMapping("/generateQR")
    public ResponseEntity<byte[]> generateQRCode(@RequestBody UserDetails userDetails) {
        String details = generateDetailsString(userDetails);
        byte[] qrCode = qrCodeGenerator.generate(details);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(qrCode.length);
        return new ResponseEntity<>(qrCode, headers, HttpStatus.OK);
    }




    // Méthode pour décoder le code QR dans le contrôleur
    @PostMapping("/decodeQR")
    public ResponseEntity<UserDetails> decodeQRCode(@RequestBody String qrCodeData) {
        UserDetails userDetails = decodeDetails(qrCodeData); // Method to extract details from QR code
        return ResponseEntity.ok(userDetails);
    }

    // Method to display document details in the frontend
    @GetMapping("/documentDetails")
    public ResponseEntity<UserDetails> getDocumentDetails(@RequestParam String qrCodeData) {
        UserDetails userDetails = decodeDetails(qrCodeData); // Method to extract details from QR code
        return ResponseEntity.ok(userDetails);
    }


}