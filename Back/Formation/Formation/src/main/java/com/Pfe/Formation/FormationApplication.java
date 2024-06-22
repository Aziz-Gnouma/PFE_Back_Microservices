package com.Pfe.Formation;

import com.Pfe.Formation.Repo.CategoryRepo;
import com.Pfe.Formation.Services.FormationService;
import com.Pfe.Formation.entity.Category;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FormationApplication {
	@Autowired
	private FormationService FormationService;
	public static void main(String[] args) {
		SpringApplication.run(FormationApplication.class, args);
	}

	@PostConstruct
	public void initializeCategories() {
		FormationService.initializeCategories();
	}


}
