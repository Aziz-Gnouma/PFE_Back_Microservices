package com.example.Carriere.repository;

import com.example.Carriere.entity.Structure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Structure_repo extends JpaRepository<Structure, Integer> {


    List<Structure> findByMatricule(String matricule);
}