package com.example.Carriere.service;

import com.example.Carriere.entity.Carriere;
import com.example.Carriere.repository.Carriere_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Carrier_service {
    @Autowired
    private  Carriere_repo Carriere_repo;


    public ResponseEntity<String> create( Carriere Carriere) {
        Optional<Carriere> existingCarriere = Carriere_repo.findById(Carriere.getCin());
        if (existingCarriere.isPresent()) {
            throw new RuntimeException("Carriere with the provided cin already exists");
        }
        try {
            Carriere_repo.save(Carriere);
            return ResponseEntity.ok("Carriere created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error in carriere ");
        }
    }


    public Optional<Carriere> afficher(int cin) {
        return Carriere_repo.findById(cin);
    }

    public List<Carriere> getAllCarrieres() {
        return Carriere_repo.findAll();
    }

    public ResponseEntity<String> deleteCarriere(int cin) {
        try {
            Carriere_repo.deleteById(cin);
            return ResponseEntity.ok("Carriere with cin: " + cin + " deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting carriere with cin: " + cin);
        }
    }

    private boolean isNotBlank(String str) {
        return str != null && !str.isEmpty();
    }
    public ResponseEntity<String> updateCarriereById(int id, Carriere updatedCarriere) {
        // Fetch the existing Carriere by ID
        Optional<Carriere> carriereOptional = Carriere_repo.findById(id);
        if (carriereOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating Carriere with ID " + id);        }

        Carriere existingCarriere = carriereOptional.get();

        // Update fields with values from updatedCarriere
        if (isNotBlank(updatedCarriere.getSalaire())) {
            existingCarriere.setSalaire(updatedCarriere.getSalaire());
        }
        if (isNotBlank(updatedCarriere.getCategorie())) {
            existingCarriere.setCategorie(updatedCarriere.getCategorie());
        }
        if (updatedCarriere.getDateEntree() != null) {
            existingCarriere.setDateEntree(updatedCarriere.getDateEntree());
        }
        if (isNotBlank(updatedCarriere.getFonction())) {
            existingCarriere.setFonction(updatedCarriere.getFonction());
        }
        if (isNotBlank(updatedCarriere.getGrade())) {
            existingCarriere.setGrade(updatedCarriere.getGrade());
        }
        if (isNotBlank(updatedCarriere.getNatureDiplome())) {
            existingCarriere.setNatureDiplome(updatedCarriere.getNatureDiplome());
        }
        if (isNotBlank(updatedCarriere.getNiveauEducation())) {
            existingCarriere.setNiveauEducation(updatedCarriere.getNiveauEducation());
        }
        if (isNotBlank(updatedCarriere.getLanguesMaitrisees())) {
            existingCarriere.setLanguesMaitrisees(updatedCarriere.getLanguesMaitrisees());
        }
        if (isNotBlank(updatedCarriere.getExperienceProfessionnelle())) {
            existingCarriere.setExperienceProfessionnelle(updatedCarriere.getExperienceProfessionnelle());
        }
        if (isNotBlank(updatedCarriere.getCompetencesSpecialisees())) {
            existingCarriere.setCompetencesSpecialisees(updatedCarriere.getCompetencesSpecialisees());
        }
        if (updatedCarriere.getDateDepart() != null) {
            existingCarriere.setDateDepart(updatedCarriere.getDateDepart());
        }

        // Save the updated Carriere
        try {
            Carriere_repo.save(existingCarriere);
            return ResponseEntity.ok("Carriere with ID " + id + " updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating Carriere with ID " + id);
        }
    }
}
