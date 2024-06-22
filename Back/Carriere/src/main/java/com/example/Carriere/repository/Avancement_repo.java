package com.example.Carriere.repository;

import com.example.Carriere.entity.Avancement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Avancement_repo  extends JpaRepository<Avancement, Integer> {


    List<Avancement> findByMatricule(String matricule);
    @Query("SELECT a FROM Avancement a WHERE a.matricule = :matricule AND a.DateAjouter = (SELECT MAX(a2.DateAjouter) FROM Avancement a2 WHERE a2.matricule = :matricule)")
    List<Avancement> findByMatriculeAndLatestDateAjouter(@Param("matricule") String matricule);
}
