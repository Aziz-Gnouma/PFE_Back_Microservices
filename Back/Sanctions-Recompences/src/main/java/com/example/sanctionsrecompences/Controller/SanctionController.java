package com.example.sanctionsrecompences.Controller;

import com.example.sanctionsrecompences.DAO.SanctionRepository;
import com.example.sanctionsrecompences.Entity.Reward;
import com.example.sanctionsrecompences.Entity.Sanction;
import com.example.sanctionsrecompences.Service.SanctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/sanctions")
public class SanctionController {

    private final SanctionService sanctionService;
    private final SanctionRepository sanctionRepository;
    @Autowired
    public SanctionController(SanctionService sanctionService, SanctionRepository sanctionRepository) {
        this.sanctionService = sanctionService;
        this.sanctionRepository = sanctionRepository;
    }

    @PostMapping("/ajout/{cin}")
        public ResponseEntity<Sanction> ajouterSanction(@PathVariable Integer cin, @RequestBody Sanction sanction) {
        sanction.setMatricule(cin);

        Sanction nouvelleSanction = sanctionService.ajouterSanction(sanction);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouvelleSanction);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> annulerSanction(@PathVariable Integer id) {
        sanctionService.annulerSanction(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Sanction> modifierSanction(@PathVariable Integer id, @RequestBody Sanction sanction) {
        Sanction sanctionModifiee = sanctionService.modifierSanction(id, sanction);
        if (sanctionModifiee != null) {
            return ResponseEntity.ok(sanctionModifiee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<Sanction>> getallSanctions() {
        List<Sanction> sanctions = sanctionService.listerSanctions();
        return new ResponseEntity<>(sanctions, HttpStatus.OK);
    }
    @GetMapping("/{sanctionId}")
    public ResponseEntity<Sanction> getSanctionById(@PathVariable Integer sanctionId) {
        Optional<Sanction> sanction = sanctionRepository.findById(sanctionId);
        if (sanction.isPresent()) {
            return ResponseEntity.ok(sanction.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Sanction>> getSanctionsByUserId(@PathVariable Integer userId) {
        List<Sanction> sanctions = sanctionService.getSanctionsByUserId(userId);
        if (sanctions != null && !sanctions.isEmpty()) {
            return ResponseEntity.ok(sanctions);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    public static Integer convertStringToInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    @GetMapping("/matricule/{matricule}")
    public List<Sanction> filterSanctionsByMatricule(@PathVariable String matricule) {
        Integer matriculeInt = convertStringToInteger(matricule);
        if (matriculeInt != null) {
            return sanctionRepository.findByMatriculeContainingIgnoreCase(matriculeInt);
        } else {
            return Collections.emptyList();
        }
    }
    @GetMapping("/list")
    public ResponseEntity<List<Sanction>> listsanctions() {
        try {
            List<Sanction> listeSanctions = sanctionService.listerSanctions();
            return ResponseEntity.ok(listeSanctions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/enterprise/{entreprise}")
    public ResponseEntity<List<Sanction>> getSanctionsByEnterprise(@PathVariable String entreprise) {
        List<Sanction> sanctions = sanctionService.getSanctionsByEnterprise(entreprise);
        if (sanctions != null && !sanctions.isEmpty()) {
            return ResponseEntity.ok(sanctions);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/totalSanctions/{entrepriseName}")
    public long getTotalSanctionsByEntreprise(@PathVariable String entrepriseName) {
        return sanctionService.getTotalSanctionsByEntreprise(entrepriseName);
    }



}
