package com.example.documentauth.repository;

import com.example.documentauth.entity.Doc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocRepository extends JpaRepository<Doc, Long> {
}
