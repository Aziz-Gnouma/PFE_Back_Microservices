package com.Salaire.Salaire.controller;

import com.Salaire.Salaire.Repository.salaireRepo;
import com.Salaire.Salaire.Services.PDFGeneratorService;
import com.Salaire.Salaire.Services.SalaireService;
import com.Salaire.Salaire.entity.*;
import com.itextpdf.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.YearMonth;
import java.util.*;

import static com.Salaire.Salaire.entity.DocumentUtils.decodeDetails;
import static com.Salaire.Salaire.entity.DocumentUtils.generateDetailsString;

@RestController
public class SalaireController {

    @Autowired
    private SalaireService salaireService;

    @PostMapping("/calculerIRPP")
    public Map<String, Double> calculerIRPP(@RequestBody IRPPRequest request) {
        return salaireService.CalculerIRPP(
                request.getNatureRevenuImposable(),
                request.getParentsEnCharge(),
                request.getAutresDeductions(),
                request.getMontantMensuel(),
                request.isChefDeFamille(),
                request.getEnfants(),
                request.getEnfantsSansBourse(),
                request.getEnfantsInfirmes()
        );
    }

    @PostMapping("/calculerSalaire")
    public String calculerSalaire(@RequestBody IRPPRequest request) {
        return salaireService.CalculSalaire(
                request.getMatricule(),
                request.getNatureRevenuImposable(),
                request.getParentsEnCharge(),
                request.getAutresDeductions(),
                request.getMontantMensuel(),
                request.isChefDeFamille(),
                request.getEnfants(),
                request.getEnfantsSansBourse(),
                request.getEnfantsInfirmes(),
                request.getNomEntreprise(),
                request.getAdresseEntreprise(),
                request.getAffiliationCss(),
                request.getCinEmploye(),
                request.getNomEmploye(),
                request.getAdresse(),
                request.getNCss(),
                request.getSituationFamiliale(),
                request.getFonction(),
                request.isSecteurPrive()

        );
    }

    @Autowired
    private PDFGeneratorService pdfGeneratorService;
    @Autowired
    private salaireRepo SalaireRepo;

    @Autowired
    private QRCodeGenerator qrCodeGenerator; // Autowire QRCodeGenerator

    @GetMapping("/GenerateFiche/{matricule}/{yearMonth}")
    public void generatePayslip(@PathVariable String matricule, @PathVariable @DateTimeFormat(pattern = "yyyy-MM") YearMonth yearMonth, HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        int month = yearMonth.getMonthValue();
        int year = yearMonth.getYear();

        FicheDePaie ficheDePaie = SalaireRepo.findByMatriculeAndMonthAndYear(matricule, month, year);


        if (ficheDePaie != null) {
            Payslip payslip = new Payslip();
            payslip.setMontantMensuel(ficheDePaie.getMontantMensuel());
            payslip.setCotisation(ficheDePaie.getCotisation());
            payslip.setSalaireImposable(ficheDePaie.getSalaireImposable());
            payslip.setIrppMensuel(ficheDePaie.getIrppMensuel());
            payslip.setCssMensuel(ficheDePaie.getCssMensuel());
            payslip.setSalaireNet(ficheDePaie.getSalaireNet());
            payslip.setMatricule(ficheDePaie.getMatricule());
            payslip.setSecteurPrive(ficheDePaie.isSecteurPrive());
            payslip.setDateAjouter(ficheDePaie.getDateAjouter());
            payslip.setNomEntreprise(ficheDePaie.getNomEntreprise());
            payslip.setAdresseEntreprise(ficheDePaie.getAdresseEntreprise());
            payslip.setAffiliationCss(ficheDePaie.getAffiliationCss());
            payslip.setCinEmploye(ficheDePaie.getCinEmploye());
            payslip.setNomEmploye(ficheDePaie.getNomEmploye());
            payslip.setAdresse(ficheDePaie.getAdresse());
            payslip.setNCss(ficheDePaie.getNCss());
            payslip.setSituationFamiliale(ficheDePaie.getSituationFamiliale());
            payslip.setFonction(ficheDePaie.getFonction());

            try {
                pdfGeneratorService.generatePayslip(response, payslip);
                response.getOutputStream().flush();
            } catch (IOException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            // FicheDePaie not found
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @GetMapping({"/AllFicheDePaie"})
    public List<FicheDePaie> getAllFinancieresByMatricule() {
        return salaireService.getAllFicheDePaie();
    }


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


}
