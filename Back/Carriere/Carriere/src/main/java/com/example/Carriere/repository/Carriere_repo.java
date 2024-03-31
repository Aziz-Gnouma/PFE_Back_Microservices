package com.example.Carriere.repository;

import com.example.Carriere.entity.Carriere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Carriere_repo extends JpaRepository<Carriere, Integer> {

}
