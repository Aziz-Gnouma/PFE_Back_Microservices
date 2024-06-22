package com.example.congesabs.Service;
import com.example.congesabs.entity.Demande;
import com.example.congesabs.repository.DemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    public Demande createDemande(Demande demande) {
        // Ajoutez ici la logique métier nécessaire, par exemple, la validation des dates
        return demandeRepository.save(demande);
    }
    public Demande updateDemandeStatus(Long id, String status) {
        // Find the demande by its ID
        Demande demande = demandeRepository.findById(id).orElse(null);
        if (demande != null) {
            // Update the status of the demande
            demande.setStatus(status);
            // Save the updated demande
            return demandeRepository.save(demande);
        }
        return null; // Return null if demande not found
    }

    public List<Demande> getPendingDemandes() {
        return demandeRepository.findByStatusEqualsIgnoreCase("pending");
    }


    public Map<String, Integer> getPreviousDaysRemaining(String matricule) {
        List<Demande> demandes = demandeRepository.findByMatricule(matricule);
        Map<String, Integer> previousDaysRemaining = new HashMap<>();

        // Parcourir toutes les demandes de congés pour cet employé
        for (Demande demande : demandes) {
            String typeConge = demande.getReason();
            int nombreJoursDemandes = demande.getNombreJoursDemandes();

            // Mettre à jour les jours restants précédents pour ce type de congé
            // Si le type de congé existe déjà dans la map, ajoutez le nombre de jours demandés, sinon initialisez-le à la demande actuelle
            previousDaysRemaining.put(typeConge, previousDaysRemaining.getOrDefault(typeConge, 0) + nombreJoursDemandes);
        }

        return previousDaysRemaining;
    }
}

