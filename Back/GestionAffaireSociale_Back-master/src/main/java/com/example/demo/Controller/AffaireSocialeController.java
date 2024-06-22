package com.example.demo.Controller;

import com.example.demo.Entity.AffaireSociale;
import com.example.demo.Service.AffaireSocialeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/affaires-sociales")
@CrossOrigin(origins = "http://localhost:4200")
public class AffaireSocialeController {

    private final AffaireSocialeService affaireSocialeService;

    @Autowired
    public AffaireSocialeController(AffaireSocialeService affaireSocialeService) {
        this.affaireSocialeService = affaireSocialeService;
    }

    @PostMapping("/ajout")
    public ResponseEntity<AffaireSociale> ajouterAffaire(@RequestBody AffaireSociale affaireSociale) {
        AffaireSociale nouvelleAffaire = affaireSocialeService.ajouterAffaire(affaireSociale);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouvelleAffaire);
    }
    @PostMapping("/save")
    public ResponseEntity<AffaireSociale> saveAffaireSociale( @RequestBody AffaireSociale affaireSociale) {
        AffaireSociale savedAffaire = affaireSocialeService.saveAffaireSociale( affaireSociale);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAffaire);
    }
    @PostMapping("/save-all")
    public ResponseEntity<List<AffaireSociale>> sauvegarderToutesLesAffaires(@RequestBody List<AffaireSociale> affairesSociales) {
        List<AffaireSociale> affairesSauvegardees = affaireSocialeService.sauvegarderToutesLesAffaires(affairesSociales);
        return ResponseEntity.status(HttpStatus.CREATED).body(affairesSauvegardees);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> annulerAffaire(@PathVariable Long id) {
        affaireSocialeService.annulerAffaire(id);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/GetAffaire/{matriculeConjoint}")
    public List<AffaireSociale> getAffaireById(@PathVariable String matriculeConjoint) {
        return affaireSocialeService.getAffaireBymatricule(matriculeConjoint);


    }
    @GetMapping({"/GetTitularisation/{id}"})
    public List<AffaireSociale> GetTitularisationByMat(@PathVariable String id) {
        return affaireSocialeService.getAllTAffaireSocialeByMatricule(id);
    }

    @GetMapping("consulter/{id}")
    public ResponseEntity<AffaireSociale> consulterAffaire(@PathVariable Long id) {
        AffaireSociale affaireSociale = affaireSocialeService.consulterAffaire(id);
        if (affaireSociale != null) {
            return ResponseEntity.ok(affaireSociale);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<AffaireSociale>> listerAffaires() {
        List<AffaireSociale> listeAffaires = affaireSocialeService.listerAffaires();
        return ResponseEntity.ok(listeAffaires);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Map<String, AffaireSociale>> modifierAffaire(@PathVariable Long id, @RequestBody AffaireSociale affaireSociale) {
        Map<String, AffaireSociale> updatedAffaireMap = affaireSocialeService.updateAffaireWithPrevious(id, affaireSociale);

        if (updatedAffaireMap != null) {
            return ResponseEntity.ok(updatedAffaireMap);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
