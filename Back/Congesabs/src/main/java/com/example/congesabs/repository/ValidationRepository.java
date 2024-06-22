package com.example.congesabs.repository;

import com.example.congesabs.entity.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationRepository extends JpaRepository<Validation, Long> {
	// Add custom query methods if needed
}
