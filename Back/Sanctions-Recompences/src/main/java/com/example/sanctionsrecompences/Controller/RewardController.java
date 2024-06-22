package com.example.sanctionsrecompences.Controller;



import com.example.sanctionsrecompences.DAO.RewardRepository;
import com.example.sanctionsrecompences.DAO.SanctionRepository;
import com.example.sanctionsrecompences.Entity.Reward;
import com.example.sanctionsrecompences.Entity.Sanction;
import com.example.sanctionsrecompences.Service.RewardService;
import com.example.sanctionsrecompences.Service.SanctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {
    @Autowired
    private RewardService rewardService;
    @Autowired
    private RewardRepository rewardRepository;

    public RewardController(RewardService rewardService, RewardRepository rewardRepository) {
        this.rewardService = rewardService;
        this.rewardRepository = rewardRepository;
    }
    @GetMapping
    public ResponseEntity<List<Reward>> getAllRewards() {
        List<Reward> rewards = rewardService.getAllRewards();
        return new ResponseEntity<>(rewards, HttpStatus.OK);
    }

    @GetMapping("/{Rewardid}")
    public ResponseEntity<Reward> getRewardById(@PathVariable Long Rewardid) {
        return rewardService.getRewardById(Rewardid)
                .map(reward -> new ResponseEntity<>(reward, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/ajout/{cin}")
    public ResponseEntity<Reward> addReward(@PathVariable Integer cin,@RequestBody Reward reward) {
        reward.setMatricule(cin);
        Reward newReward = rewardService.saveReward(reward);
        return new ResponseEntity<>(newReward, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Reward> updateReward(@PathVariable Long id, @RequestBody Reward reward) {
        Reward updatedReward = rewardService.updateReward(id, reward);
        if (updatedReward != null) {
            return new ResponseEntity<>(updatedReward, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public static Integer convertStringToInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    @GetMapping("/matricule/{matricule}")
    public List<Reward> filterrewardsByMatricule(@PathVariable String matricule) {
        Integer matriculeInt = convertStringToInteger(matricule);
        if (matriculeInt != null) {
            return rewardRepository.findByMatriculeContainingIgnoreCase(matriculeInt);
        } else {
            return Collections.emptyList();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReward(@PathVariable Long id) {
        rewardService.deleteReward(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/list")
    public ResponseEntity<List<Reward>> listRewards() {
        List<Reward> rewards = rewardService.getAllRewards();
        return ResponseEntity.ok(rewards);
    }
    @GetMapping("/enterprise/{entreprise}")
    public ResponseEntity<List<Reward>> getRewardsByEnterprise(@PathVariable String entreprise) {
        List<Reward> rewards = rewardService.getRewardsByEnterprise(entreprise); 
        if (rewards != null && !rewards.isEmpty()) {
            return ResponseEntity.ok(rewards);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/totalRewards/{entrepriseName}")
    public long getTotalRewardsByEntreprise(@PathVariable String entrepriseName) {
        return rewardService.getTotalRewardsByEntreprise(entrepriseName);
    }
}
