package com.example.cvai.Repository;

import com.example.cvai.Entity.CV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// CV Repository interface
@Repository
public interface CVRepository extends JpaRepository<CV, Long> {
}
