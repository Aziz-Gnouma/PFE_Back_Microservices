package com.example.sanctionsrecompences.DAO;
import com.example.sanctionsrecompences.Entity.Sanction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanctionRepository extends JpaRepository<Sanction, Integer> {
    @Query("SELECT s FROM Sanction s WHERE s.matricule = :matricule")
    List<Sanction> findByMatriculeContainingIgnoreCase(@Param("matricule") Integer matricule);
    List<Sanction> findByMatricule(Integer userId);
    List<Sanction> findByEntreprise(String entreprise);

    @Query("SELECT COUNT(u) FROM Sanction u WHERE u.entreprise = :entrepriseName")
    long countSanctionsByEntreprise(@Param("entrepriseName") String entrepriseName);

}
