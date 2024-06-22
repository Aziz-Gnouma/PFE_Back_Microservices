package com.example.demo.DAO;


import com.example.demo.Entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
    // Vous pouvez ajouter des méthodes personnalisées de requête ici si nécessaire
}
