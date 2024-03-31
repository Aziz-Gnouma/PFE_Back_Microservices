package com.example.Depart.service;

import com.example.Depart.entity.Depart;
import com.example.Depart.repository.Depart_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Depart_service {
    @Autowired
    private Depart_repo Depart_repo;

    public ResponseEntity<String> create(Depart depart) {
        // Validate input data
        if (depart.getCin() == null || Depart_repo.existsById(depart.getCin())) {
            return ResponseEntity.badRequest().body("Invalid or duplicate Cin provided.");
        }

        try {
            Depart_repo.save(depart);
            return ResponseEntity.ok("Depart created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating Depart: " + e.getMessage());
        }
    }

    public Optional<Depart> afficher(int cin) {
        return Depart_repo.findById(cin);
    }

    public List<Depart> getAllDeparts() {
        return Depart_repo.findAll();
    }

    public ResponseEntity<String> deleteDepart(int cin) {
        try {
            Depart_repo.deleteById(cin);
            return ResponseEntity.ok("Depart with cin: " + cin + " deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting Depart with cin: " + cin);
        }
    }

    private boolean isNotBlank(String str) {
        return str != null && !str.isEmpty();
    }

}
