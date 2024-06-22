package com.example.sanctionsrecompences.Service;

import com.example.sanctionsrecompences.DAO.SanctionRepository;
import com.example.sanctionsrecompences.Entity.Sanction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SanctionService {

    private final SanctionRepository sanctionRepository;

    @Autowired
    public SanctionService(SanctionRepository sanctionRepository) {
        this.sanctionRepository = sanctionRepository;
    }

    public Sanction ajouterSanction(Sanction sanction) {
        return sanctionRepository.save(sanction);
    }

    public void annulerSanction(Integer id) {
        sanctionRepository.deleteById(id);
    }

    public Sanction modifierSanction(Integer id, Sanction sanction) {
        Optional<Sanction> existingSanction = sanctionRepository.findById(id);
        if (existingSanction.isPresent()) {
            Sanction sanctionToUpdate = existingSanction.get();
            // Mettre à jour les champs modifiables
            sanctionToUpdate.setType(sanction.getType());
            sanctionToUpdate.setDateDemission(sanction.getDateDemission());
            sanctionToUpdate.setAutorite(sanction.getAutorite());
            sanctionToUpdate.setDescription(sanction.getDescription());
            sanctionToUpdate.setGravite(sanction.getGravite());
            sanctionToUpdate.setDuree(sanction.getDuree());
            sanctionToUpdate.setStatutA(sanction.getStatutA());
            // Mettre à jour d'autres champs selon le besoin
            return sanctionRepository.save(sanctionToUpdate);
        } else {
            return null;
        }
    }
    public List<Sanction> getSanctionsByEnterprise(String entreprise) {
        // Implement logic to retrieve sanctions by enterprise name
        return sanctionRepository.findByEntreprise(entreprise);
    }
    public List<Sanction> getSanctionsByUserId(Integer userId) {
        // Assuming you have a method in your repository to fetch sanctions by user ID
        return sanctionRepository.findByMatricule(userId);
    }
    public Sanction getSanctionById(Integer id) {
        Optional<Sanction> sanction = sanctionRepository.findById(id);
        return sanction.orElse(null);
    }

    public Sanction consulterSanction(Integer id) {
        Optional<Sanction> sanction = sanctionRepository.findById(id);
        return sanction.orElse(null);
    }


    public List<Sanction> listerSanctions() {
        return sanctionRepository.findAll();
    }

    public long getTotalSanctionsByEntreprise(String entrepriseName) {
        return sanctionRepository.countSanctionsByEntreprise(entrepriseName);
    }

}
