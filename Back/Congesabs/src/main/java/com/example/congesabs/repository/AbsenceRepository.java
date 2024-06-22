package com.example.congesabs.repository;

import com.example.congesabs.Service.AbsenceStatus;
import com.example.congesabs.entity.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    List<Absence> findByStatus(AbsenceStatus status);
    List<Absence> findByMatricule(String matricule);

}