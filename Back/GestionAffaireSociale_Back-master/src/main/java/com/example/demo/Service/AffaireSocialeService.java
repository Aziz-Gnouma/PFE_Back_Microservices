package com.example.demo.Service;

import com.example.demo.DAO.AffaireSocialeRepository;
import com.example.demo.Entity.AffaireSociale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AffaireSocialeService {

    private final AffaireSocialeRepository affaireSocialeRepository;

    @Autowired
    public AffaireSocialeService(AffaireSocialeRepository affaireSocialeRepository) {
        this.affaireSocialeRepository = affaireSocialeRepository;
    }

    // Ajouter une affaire sociale
    public AffaireSociale ajouterAffaire(AffaireSociale affaireSociale) {
        Date dateAjout = new Date();
        affaireSociale.setAffiliationLe(dateAjout);

        return affaireSocialeRepository.save(affaireSociale);
    }

    // Annuler une affaire sociale
    public void annulerAffaire(Long id) {
        affaireSocialeRepository.deleteById(id);
    }

    // Modifier une affaire sociale
    public AffaireSociale modifierAffaire(Long id, AffaireSociale affaireSociale) {
        Optional<AffaireSociale> existingAffaire = affaireSocialeRepository.findById(id);
        if (existingAffaire.isPresent()) {
            AffaireSociale affaireToUpdate = existingAffaire.get();
            // Mettre à jour les champs modifiables
            affaireToUpdate.setSituationFamiliale(affaireSociale.getSituationFamiliale());
            affaireToUpdate.setMatriculeConjoint(affaireSociale.getMatriculeConjoint());
            affaireToUpdate.setPrenomConjoint(affaireSociale.getPrenomConjoint());
            affaireToUpdate.setNbEnfants(affaireSociale.getNbEnfants());
            affaireToUpdate.setNbEnfantsImposables(affaireSociale.getNbEnfantsImposables());
            // Mettre à jour d'autres champs selon le besoin
            return affaireSocialeRepository.save(affaireToUpdate);
        } else {
            return null;
        }
    }
    public List getAffaireBymatricule(String matriculeConjoint) {
        return  affaireSocialeRepository.findByMatriculeConjointAndLatestDateAjouter(matriculeConjoint);

    }

    public List<AffaireSociale> getAllTAffaireSocialeByMatricule(String matriculeConjoint) {
        return affaireSocialeRepository.findByMatriculeConjoint(matriculeConjoint);
    }

    // Récupérer l'ancienne et la nouvelle version d'une affaire sociale après une mise à jour
    public Map<String, AffaireSociale> updateAffaireWithPrevious(Long id, AffaireSociale affaireSociale) {
        // Retrieve the previous version of the AffaireSociale object from the database
        Optional<AffaireSociale> optionalPreviousAffaire = affaireSocialeRepository.findById(id);

        if (optionalPreviousAffaire.isPresent()) {
            AffaireSociale previousAffaire = optionalPreviousAffaire.get();

            // Make a copy of the previous AffaireSociale object
            AffaireSociale previousAffaireCopy = new AffaireSociale();
            previousAffaireCopy.setId(previousAffaire.getId());
            previousAffaireCopy.setSituationFamiliale(previousAffaire.getSituationFamiliale());
            previousAffaireCopy.setMatriculeConjoint(previousAffaire.getMatriculeConjoint());
            previousAffaireCopy.setPrenomConjoint(previousAffaire.getPrenomConjoint());
            previousAffaireCopy.setNbEnfants(previousAffaire.getNbEnfants());
            previousAffaireCopy.setNbEnfantsImposables(previousAffaire.getNbEnfantsImposables());
            previousAffaireCopy.setChefDeFamille(previousAffaire.isChefDeFamille());
            previousAffaireCopy.setSalaireUnique(previousAffaire.isSalaireUnique());
            previousAffaireCopy.setAllocFamiliale(previousAffaire.isAllocFamiliale());
            previousAffaireCopy.setSecuriteSociale(previousAffaire.getSecuriteSociale());
            previousAffaireCopy.setNomAssurance(previousAffaire.getNomAssurance());
            previousAffaireCopy.setAffiliationLe(previousAffaire.getAffiliationLe());
            previousAffaireCopy.setExonere(previousAffaire.isExonere());
            previousAffaireCopy.setAffiliationRegime(previousAffaire.isAffiliationRegime());
            previousAffaireCopy.setAffiliationAssurance(previousAffaire.isAffiliationAssurance());
            previousAffaireCopy.setAffiliationMutuelle(previousAffaire.isAffiliationMutuelle());


            // Update the AffaireSociale object with the new values
            AffaireSociale updatedAffaire = affaireSocialeRepository.save(affaireSociale);

            // Create a map to hold both previous and updated versions
            Map<String, AffaireSociale> result = new HashMap<>();
            result.put("previousAffaire", previousAffaireCopy);
            result.put("updatedAffaire", updatedAffaire);

            return result;
        } else {
            // Handle case when the previous version is not found
            return null;
        }
    }
    public List<AffaireSociale> sauvegarderToutesLesAffaires(List<AffaireSociale> affairesSociales) {
        Date dateAjout = new Date();
        for (AffaireSociale affaire : affairesSociales) {
            affaire.setAffiliationLe(dateAjout);
        }
        return affaireSocialeRepository.saveAll(affairesSociales);
    }

    // Consulter une affaire sociale par son ID
    public AffaireSociale consulterAffaire(Long id) {
        Optional<AffaireSociale> affaireSociale = affaireSocialeRepository.findById(id);
        return affaireSociale.orElse(null);
    }
    public AffaireSociale saveAffaireSociale( AffaireSociale affaireSociale) {
        // You might want to perform some validation or business logic here before saving
        return affaireSocialeRepository.save(affaireSociale);
    }

    // Lister toutes les affaires sociales
    public List<AffaireSociale> listerAffaires() {
        return affaireSocialeRepository.findAll();
    }
}
