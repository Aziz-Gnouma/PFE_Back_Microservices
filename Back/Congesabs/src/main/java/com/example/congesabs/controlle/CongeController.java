package com.example.congesabs.controlle;


import com.example.congesabs.Service.DemandeService;
import com.example.congesabs.entity.Demande;
import com.example.congesabs.repository.DemandeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@RestController
@RequestMapping("/conges")
public class CongeController {


    @Autowired
    private DemandeService demandeService;
    @Autowired
    private DemandeRepository demandeRepository;
    @PostMapping
    public ResponseEntity<?> createDemande(@Valid @RequestBody Demande demande, BindingResult result) {
        if (result.hasErrors()) {
            // Handle validation errors
            return ResponseEntity.badRequest().body("Validation failed");
        }

        // Check if the conges is cumulative
        boolean isCumulative = demande.getDateDebut().compareTo(demande.getDateFin()) <= 0 && isCumulative(demande.getDateDebut());

        // Proceed with processing the demande
        demande.setCumulative(isCumulative);
        return ResponseEntity.ok(demandeService.createDemande(demande));
    }

    private boolean isCumulative(Date date) {
        // Implement your logic here to determine if the conges is cumulative or not
        // Return true if cumulative, false otherwise
        TimeZone timeZone = TimeZone.getDefault();
        LocalDate localDate = LocalDate.ofInstant(date.toInstant(), timeZone.toZoneId());
        LocalDate currentDate = LocalDate.now();

        long daysBetween = ChronoUnit.DAYS.between(localDate, currentDate);
        return daysBetween >= 0;
    }
    @GetMapping("/pending")
    public ResponseEntity<List<Demande>> getPendingDemandes() {
        List<Demande> allDemandes = demandeRepository.findAll();
        return ResponseEntity.ok(allDemandes);
    }
    @GetMapping
    public ResponseEntity<List<Demande>> getAllDemandes() {
        List<Demande> allDemandes = demandeRepository.findAll();
        return ResponseEntity.ok(allDemandes);
    }
    @GetMapping("/accepted")
    public ResponseEntity<List<Demande>> getAcceptedDemandes() {
        List<Demande> acceptedDemandes = demandeRepository.findByStatus("accepted");
        return ResponseEntity.ok(acceptedDemandes);
    }
    @GetMapping("/previous-days-remaining")
    public ResponseEntity<Map<String, Integer>> getPreviousDaysRemaining(@RequestParam String matricule) {
        Map<String, Integer> previousDaysRemaining = demandeService.getPreviousDaysRemaining(matricule);
        return ResponseEntity.ok(previousDaysRemaining);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDemandeStatus(@PathVariable Long id, @RequestBody Demande demande) {
        Demande updatedDemande = demandeService.updateDemandeStatus(id, demande.getStatus());
        if (updatedDemande != null) {
            return ResponseEntity.ok(updatedDemande);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/demandes")
    public ResponseEntity<List<Demande>> getAllDemandesByMatricule(@RequestParam String matricule) {
        List<Demande> demandesByMatricule = demandeRepository.findByMatricule(matricule);
        return ResponseEntity.ok(demandesByMatricule);
    }


}