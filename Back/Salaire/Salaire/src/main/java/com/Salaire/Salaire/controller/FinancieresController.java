package com.Salaire.Salaire.controller;

import com.Salaire.Salaire.Services.FinancieresService;
import com.Salaire.Salaire.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class FinancieresController {

    @Autowired
    private FinancieresService financieresService;

    @PostMapping("/financieres")
    public ResponseEntity<Financieres> saveFinancieresWithBankAndAgency(@RequestBody FinancieresRequest request) {
        Financieres financieres = new Financieres();
        financieres.setMatricule(request.getMatricule());
        financieres.setGrilleSalaire(request.getGrilleSalaire());
        financieres.setSalaireBase(request.getSalaireBase());
        financieres.setModePaiement(request.getModePaiement());
        financieres.setNumeroCompte(request.getNumeroCompte());
        financieres.setMontantAssurance(request.getMontantAssurance());
        financieres.setMontantMutuelle(request.getMontantMutuelle());
        Financieres savedFinancieres = financieresService.saveFinancieresWithBankAndAgency(financieres, request.getBankName(), request.getAgencyName());
        return new ResponseEntity<>(savedFinancieres, HttpStatus.CREATED);
    }

    @GetMapping("/agences/{bankName}")
    public ResponseEntity<String> getAllAgenciesOfBank(@PathVariable String bankName) {
        List<AgenceDTO> agencies = financieresService.getAllAgenciesOfBank(bankName);
        String formattedAgencies = financieresService.formatAgencies(agencies);
        return ResponseEntity.ok(formattedAgencies);
    }

    @GetMapping("/banks")
    public ResponseEntity<List<Bank>> getAllBanks() {
        List<Bank> banks = financieresService.getAllBanks();
        return new ResponseEntity<>(banks, HttpStatus.OK);
    }
    @GetMapping({"/financieres/{id}"})
    public List<Financieres> getAllFinancieresByMatricule(@PathVariable String id) {
        return financieresService.getAllFinancieresByMatricule(id);
    }
    @GetMapping({"/Userfinancieres/{id}"})
    public List<Financieres> getLastFinancieresByMatricule(@PathVariable String id) {
        return financieresService.getLastFinancieresByMatricule(id);
    }


    @GetMapping("/{bankName}")
    public ResponseEntity<Bank> getOrCreateBank(@PathVariable String bankName) {
        Bank bank = financieresService.getOrCreateBank(bankName);
        return new ResponseEntity<>(bank, HttpStatus.OK);
    }

}
