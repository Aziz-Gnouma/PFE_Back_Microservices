package com.example.demo.DAO;

import com.example.demo.Entity.AffaireSociale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AffaireSocialeRepository extends JpaRepository<AffaireSociale, Long> {


    List<AffaireSociale> findByMatriculeConjoint(String matriculeConjoint);


    @Query("SELECT a FROM AffaireSociale a WHERE a.matriculeConjoint = :matriculeConjoint AND a.affiliationLe = (SELECT MAX(a2.affiliationLe) FROM AffaireSociale a2 WHERE a2.matriculeConjoint = :matriculeConjoint)")
    List<AffaireSociale> findByMatriculeConjointAndLatestDateAjouter(@Param("matriculeConjoint") String matriculeConjoint);
}
