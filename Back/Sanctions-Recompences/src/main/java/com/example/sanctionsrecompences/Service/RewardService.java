package com.example.sanctionsrecompences.Service;


import com.example.sanctionsrecompences.DAO.RewardRepository;
import com.example.sanctionsrecompences.Entity.Reward;
import com.example.sanctionsrecompences.Entity.Sanction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RewardService {
    @Autowired
    private RewardRepository rewardRepository;

    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

    public Optional<Reward> getRewardById(Long id) {
        return rewardRepository.findById(id);
    }
    public List<Reward> getRewardsByEnterprise(String entreprise) {
        // Implement logic to retrieve sanctions by enterprise name
        return rewardRepository.findByEntreprise(entreprise);
    }
    public Reward saveReward(Reward reward) {
        return rewardRepository.save(reward);
    }

    public void deleteReward(Long id) {
        rewardRepository.deleteById(id);
    }
    public Reward updateReward(Long id, Reward reward) {
        Optional<Reward> optionalExistingReward = rewardRepository.findById(id);
        if (optionalExistingReward.isPresent()) {
            Reward existingReward = optionalExistingReward.get();
            // Copie les propriétés de la récompense mise à jour dans l'objet existant
            BeanUtils.copyProperties(reward, existingReward, "id");
            return rewardRepository.save(existingReward);
        } else {
            return null; // Si aucune récompense avec cet ID n'est trouvée
        }
    }
    public long getTotalRewardsByEntreprise(String entrepriseName) {
        return rewardRepository.countRewardsByEntreprise(entrepriseName);
    }
}
