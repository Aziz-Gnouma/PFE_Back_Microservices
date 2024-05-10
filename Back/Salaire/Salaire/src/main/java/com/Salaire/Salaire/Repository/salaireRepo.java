package com.Salaire.Salaire.Repository;

import com.Salaire.Salaire.entity.FicheDePaie;
import com.Salaire.Salaire.entity.salaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface salaireRepo extends JpaRepository<FicheDePaie, Long> {

    @Query("SELECT f FROM FicheDePaie f WHERE f.matricule = :matricule AND MONTH(f.DateAjouter) = :month AND YEAR(f.DateAjouter) = :year")
    FicheDePaie findByMatriculeAndMonthAndYear(String matricule, int month, int year);

    @Query("SELECT f FROM FicheDePaie f WHERE f.matricule = :matricule AND MONTH(f.DateAjouter) = :currentMonth AND YEAR(f.DateAjouter) = :currentYear")

    FicheDePaie findByMatriculeAndDateAjouterYearAndDateAjouterMonth(String matricule, int currentMonth, int currentYear);
}