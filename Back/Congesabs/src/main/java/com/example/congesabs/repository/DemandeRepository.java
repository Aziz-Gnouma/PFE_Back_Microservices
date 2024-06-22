package com.example.congesabs.repository;


import com.example.congesabs.entity.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {
    List<Demande> findByStatusEqualsIgnoreCase(String status);
    @Query("SELECT d FROM Demande d WHERE d.status = ?1")
    List<Demande> findByStatus(String status);
    List<Demande> findByMatricule(String matricule);
}
