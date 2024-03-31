package com.example.Carriere.repository;

import com.example.Carriere.entity.Titularisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Titularisation_repo extends JpaRepository<Titularisation, Integer> {


    List<Titularisation> findByMatricule(String matricule);
}
