package com.example.sanctionsrecompences.DAO;

import com.example.sanctionsrecompences.Entity.Reward;
import com.example.sanctionsrecompences.Entity.Sanction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {
    @Query("SELECT s FROM Reward s WHERE s.matricule = :matricule")
    List<Reward> findByMatriculeContainingIgnoreCase(@Param("matricule") Integer matricule);
    List<Reward> findByEntreprise(String entreprise);



    @Query("SELECT COUNT(u) FROM Reward u WHERE u.entreprise = :entrepriseName")
    long countRewardsByEntreprise(@Param("entrepriseName") String entrepriseName);
}
