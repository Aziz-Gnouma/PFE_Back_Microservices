package com.example.Carriere.repository;

import com.example.Carriere.entity.Avancement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Avancement_repo  extends JpaRepository<Avancement, Integer> {


    List<Avancement> findByMatricule(String matricule);
}
