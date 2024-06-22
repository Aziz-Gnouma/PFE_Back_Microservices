package com.example.documentauth.controlle;

import com.example.documentauth.Service.AttestationService;
import com.example.documentauth.entity.Attestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttestationController {

    @Autowired
    private AttestationService attestationService;

    @GetMapping("/attestation")
    public Attestation getAttestation(@RequestParam String cin) {
        return attestationService.getAttestationByCin(cin);
    }
}