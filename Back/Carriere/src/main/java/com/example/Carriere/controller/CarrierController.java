package com.example.Carriere.controller;

import com.example.Carriere.entity.*;
import com.example.Carriere.service.Carrier_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CarrierController {
    @Autowired
    private Carrier_service Carrier_service;

//    @PostMapping({"/createNewCarrier"})
//    public  ResponseEntity<String> createNewCarrier(@RequestBody Carriere Carriere) {
//        try {
//            return Carrier_service.create(Carriere);
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }    }

//    @GetMapping({"/GetCarrier/{id}"})
//    public Optional<Carriere> GetCarrierByCin(@PathVariable int id) {
//        return Carrier_service.afficher(id);
//    }

    @PostMapping("/createNewCarrier")
    public ResponseEntity<String> createNewCarrier(@RequestBody CarriereRequest carriereRequest) {
        try {
            return Carrier_service.createCarriere(  carriereRequest.getStructure() ,carriereRequest.getAvancement(),
                    carriereRequest.getTitularisation());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping({"/GetAvancement/{id}"})
    public List<Avancement> GetAvancementByMat(@PathVariable String id) {
        return Carrier_service.getAllAvancementsByMatricule(id);
    }
    @GetMapping({"/GetCarriere/{id}"})
    public Map<String, List<?>> GetCarriereByMat(@PathVariable String id) {
        return Carrier_service.getCarriereByMatricule(id);
    }
    @GetMapping({"/GetTitularisation/{id}"})
    public List<Titularisation> GetTitularisationByMat(@PathVariable String id) {
        return Carrier_service.getAllTitularisationsByMatricule(id);
    }
    @GetMapping({"/GetStructure/{id}"})
    public List<Structure> GetStructureByMat(@PathVariable String id) {
        return Carrier_service.getAllStructuresByMatricule(id);
    }

    @GetMapping({"/AllCarrier"})
    public List<Carriere> GetCarrierByCin() {
        return Carrier_service.getAllCarrieres();
    }

    @PutMapping({"/UpdateCarrier/{id}"})
    public  ResponseEntity<String> UpdateCarrier(@PathVariable int id, @RequestBody Carriere updatedCarriere) {
        try {
            return Carrier_service.updateCarriereById(id,updatedCarriere);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping({"/DeleteCarrier/{id}"})
    public ResponseEntity<String> DelCarrierByCin(@PathVariable int id) {
        return Carrier_service.deleteCarriere(id);
    }
}
