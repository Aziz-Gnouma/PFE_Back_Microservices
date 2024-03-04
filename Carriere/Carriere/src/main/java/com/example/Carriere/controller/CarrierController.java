package com.example.Carriere.controller;

import com.example.Carriere.entity.Carriere;
import com.example.Carriere.service.Carrier_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CarrierController {
    @Autowired
    private Carrier_service Carrier_service;

    @PostMapping({"/createNewCarrier"})
    public  ResponseEntity<String> createNewCarrier(@RequestBody Carriere Carriere) {
        try {
            return Carrier_service.create(Carriere);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }    }

    @GetMapping({"/GetCarrier/{cin}"})
    public Optional<Carriere> GetCarrierByCin(@PathVariable int cin) {
        return Carrier_service.afficher(cin);
    }

    @GetMapping({"/AllCarrier"})
    public List<Carriere> GetCarrierByCin() {
        return Carrier_service.getAllCarrieres();
    }

    @PutMapping({"/UpdateCarrier/{cin}"})
    public  ResponseEntity<String> UpdateCarrier(@PathVariable int cin, @RequestBody Carriere updatedCarriere) {
        try {
            return Carrier_service.updateCarriereById(cin,updatedCarriere);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping({"/DeleteCarrier/{cin}"})
    public ResponseEntity<String> DelCarrierByCin(@PathVariable int cin) {
        return Carrier_service.deleteCarriere(cin);
    }
}
