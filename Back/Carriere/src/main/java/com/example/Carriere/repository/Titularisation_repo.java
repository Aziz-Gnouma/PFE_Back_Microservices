package com.example.Carriere.repository;

import com.example.Carriere.entity.Structure;
import com.example.Carriere.entity.Titularisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Titularisation_repo extends JpaRepository<Titularisation, Integer> {


    List<Titularisation> findByMatricule(String matricule);
    @Query("SELECT a FROM Titularisation a WHERE a.matricule = :matricule AND a.DateAjouter = (SELECT MAX(a2.DateAjouter) FROM Titularisation a2 WHERE a2.matricule = :matricule)")
    List<Titularisation> findByMatriculeAndLatestDateAjouter(@Param("matricule") String matricule);
}
