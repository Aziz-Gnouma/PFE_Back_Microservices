package com.example.congesabs.repository;


import com.example.congesabs.entity.TypeCongee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeCongeeRepository extends JpaRepository<TypeCongee, Long> {
    TypeCongee findByCode(String code);
}
