package com.Pfe.Formation.Repo;

import com.Pfe.Formation.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long>{


    Category findByName(String name);
}
