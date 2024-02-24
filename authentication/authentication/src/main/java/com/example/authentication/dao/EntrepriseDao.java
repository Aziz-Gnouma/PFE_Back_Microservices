package com.example.authentication.dao;

import com.example.authentication.entity.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepriseDao extends JpaRepository<Entreprise, String> {

    boolean existsByNumFiscale(String numFiscale);

    boolean existsByEmail(String email);


}
