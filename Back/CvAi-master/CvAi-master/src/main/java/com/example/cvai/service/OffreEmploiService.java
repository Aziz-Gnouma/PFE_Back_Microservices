package com.example.cvai.service;

import com.example.cvai.Entity.OffreEmploi;
import com.example.cvai.Repository.OffreEmploiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OffreEmploiService {

    @Autowired
    private OffreEmploiRepository offreEmploiRepository;

    public OffreEmploi addOffre(OffreEmploi offreEmploi) {
        return offreEmploiRepository.save(offreEmploi);
    }

    public OffreEmploi editOffre(Long id, OffreEmploi updatedOffre) {
        Optional<OffreEmploi> optionalOffre = offreEmploiRepository.findById(id);
        if (optionalOffre.isPresent()) {
            OffreEmploi existingOffre = optionalOffre.get();
            existingOffre.setTitre(updatedOffre.getTitre());
            existingOffre.setNomEntreprise(updatedOffre.getNomEntreprise());
            existingOffre.setLieu(updatedOffre.getLieu());
            existingOffre.setSalaire(updatedOffre.getSalaire());
            existingOffre.setTypeDePoste(updatedOffre.getTypeDePoste());
            existingOffre.setDetailsEmploi(updatedOffre.getDetailsEmploi());
            existingOffre.setTempsDeTravail(updatedOffre.getTempsDeTravail());
            existingOffre.setDescriptionPoste(updatedOffre.getDescriptionPoste());
            return offreEmploiRepository.save(existingOffre);
        } else {
            return null;
        }
    }

    public void deleteOffre(Long id) {
        offreEmploiRepository.deleteById(id);
    }

    public OffreEmploi getOffreById(Long id) {
        return offreEmploiRepository.findById(id).orElse(null);
    }
    public List<OffreEmploi> getAllOffres() {
        return offreEmploiRepository.findAll();
    }
}
