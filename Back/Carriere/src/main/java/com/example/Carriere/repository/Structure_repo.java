package com.example.Carriere.repository;

import com.example.Carriere.entity.Avancement;
import com.example.Carriere.entity.Structure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Structure_repo extends JpaRepository<Structure, Integer> {


    List<Structure> findByMatricule(String matricule);
    @Query("SELECT a FROM Structure a WHERE a.matricule = :matricule AND a.DateAjouter = (SELECT MAX(a2.DateAjouter) FROM Structure a2 WHERE a2.matricule = :matricule)")
    List<Structure> findByMatriculeAndLatestDateAjouter(@Param("matricule") String matricule);
}