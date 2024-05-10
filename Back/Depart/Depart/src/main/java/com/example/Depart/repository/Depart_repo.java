package com.example.Depart.repository;

import com.example.Depart.entity.Depart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Depart_repo extends JpaRepository<Depart, Integer> {
    Optional<Depart> findByMatricule(String matricule);

}
