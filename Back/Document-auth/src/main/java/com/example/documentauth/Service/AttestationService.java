package com.example.documentauth.Service;

import com.example.documentauth.entity.Attestation;
import org.springframework.stereotype.Service;

@Service
public class AttestationService {

    // Replace this with actual data fetching logic, e.g., from a database
    public Attestation getAttestationByCin(String cin) {
        // Mock data for demonstration
        Attestation attestation = new Attestation();
        attestation.setCin(cin);
        attestation.setUserFirstName("Hamza");
        attestation.setUserLastName("Bny");
        attestation.setMatricule("Atatat2_58");
        attestation.setPays("Italy");
        attestation.setCinDate("20-09-2020");
        attestation.setDateOfBirth("20-09-2000");
        attestation.setEntrepriseName("Your Company");
        attestation.setNationality("Tunisia");
        attestation.setAdresse("Blue Center");
        attestation.setDatedebut("01-01-2018");
        attestation.setFonction("Software Engineer");
        return attestation;
    }
}
