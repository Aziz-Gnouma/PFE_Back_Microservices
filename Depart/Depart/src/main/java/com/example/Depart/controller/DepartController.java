package com.example.Depart.controller;


import com.example.Depart.entity.Depart;
import com.example.Depart.service.Depart_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DepartController {
    @Autowired
    private Depart_service Depart_service;

    @PostMapping({"/DemandeDepart"})
    public  ResponseEntity<String> createNewCarrier(@RequestBody Depart Depart) {
        try {
            return Depart_service.create(Depart);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping({"/GetDepart/{cin}"})
    public Optional<Depart> GetCarrierByCin(@PathVariable int cin) {
        return Depart_service.afficher(cin);
    }

    @GetMapping({"/AllDepart"})
    public List<Depart> GetCarrierByCin() {
        return Depart_service.getAllDeparts();
    }

//    @PutMapping({"/UpdateCarrier/{cin}"})
//    public  ResponseEntity<String> UpdateCarrier(@PathVariable int cin, @RequestBody Depart updatedDepart) {
//        try {
//            return Depart_service.updateCarriereById(cin,updatedDepart);
//        } catch (Depart_service e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }


    @DeleteMapping({"/DeleteDepart/{cin}"})
    public ResponseEntity<String> DelCarrierByCin(@PathVariable int cin) {
        return Depart_service.deleteDepart(cin);
    }
}
