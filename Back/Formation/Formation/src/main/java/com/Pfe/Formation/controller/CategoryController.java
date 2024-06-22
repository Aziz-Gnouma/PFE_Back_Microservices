package com.Pfe.Formation.controller;

import com.Pfe.Formation.DAO.CategoryWithFormationsDTO;
import com.Pfe.Formation.Repo.CategoryRepo;
import com.Pfe.Formation.Repo.FormationRepository;
import com.Pfe.Formation.entity.Category;
import com.Pfe.Formation.entity.Formation;
import com.Pfe.Formation.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryRepo categoryRepository;
    @Autowired
    private FormationRepository formationRepository;


    @GetMapping("AllCategory")
    public List<Category> getAllCategoriesByEnterprise() {
        return categoryRepository.findAll();
    }

    @PostMapping("/AjouterCategory")
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }


    @GetMapping("Category/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryWithFormationsDTO> getCategoryWithFormations(@PathVariable Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        List<Formation> formations = formationRepository.findByCategoriesId(id);

        CategoryWithFormationsDTO categoryWithFormationsDTO = new CategoryWithFormationsDTO();
        categoryWithFormationsDTO.setId(category.getId());
        categoryWithFormationsDTO.setName(category.getName());
        categoryWithFormationsDTO.setFormations(formations);

        return ResponseEntity.ok().body(categoryWithFormationsDTO);
    }

    @PutMapping("UpdateCategory/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        category.setName(categoryDetails.getName());

        return categoryRepository.save(category);
    }

    @DeleteMapping("DeleteCategory/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        categoryRepository.delete(category);

        return ResponseEntity.ok().build();
    }
}
