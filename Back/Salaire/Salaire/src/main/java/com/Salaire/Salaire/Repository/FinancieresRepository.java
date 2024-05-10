package com.Salaire.Salaire.Repository;

import com.Salaire.Salaire.entity.FicheDePaie;
import com.Salaire.Salaire.entity.Financieres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancieresRepository extends JpaRepository<Financieres, Long> {
    List<Financieres> findByMatricule(String matricule);
    @Query("SELECT a FROM Financieres a WHERE a.matricule = :matricule AND a.DateAjouter = (SELECT MAX(a2.DateAjouter) FROM Financieres a2 WHERE a2.matricule = :matricule)")
    List<Financieres> findByMatriculeAndLatestDateAjouter(@Param("matricule") String matricule);
}
