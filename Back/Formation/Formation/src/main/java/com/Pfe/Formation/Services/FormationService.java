package com.Pfe.Formation.Services;

import com.Pfe.Formation.DAO.FormationDTO;
import com.Pfe.Formation.Repo.CategoryRepo;
import com.Pfe.Formation.Repo.FormationRepository;
import com.Pfe.Formation.entity.Category;
import com.Pfe.Formation.entity.Formation;
import com.Pfe.Formation.exception.FormationNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class FormationService {

    @Autowired
    private FormationRepository formationRepository;

    public Long saveFormation(String fileName, byte[] content, String titre, String duree, String langue, String niveau,
                         String formateur, String description, String RHMatricule, String entreprise
                         , Set<Category> categories) {
        Date currentDate = new Date();
        Formation formation = new Formation();
        formation.setFileName(fileName);
        formation.setContent(content);
        formation.setTitre(titre);
        formation.setDuree(duree);
        formation.setLangue(langue);
        formation.setNiveau(niveau);
        formation.setFormateur(formateur);
        formation.setDescription(description);
        formation.setRHMatricule(RHMatricule);
        formation.setEntreprise(entreprise);
        formation.setStatut("active");
        formation.setParticipants(0);
        formation.setDateAjouter(currentDate);
        formation.setCategories(categories);

        Formation savedEntity = formationRepository.save(formation);
        return savedEntity.getId();

    }

    public List<Formation> getFormationsByCategory(String categoryName) {
        return formationRepository.findByCategoryName(categoryName);
    }
    @Transactional
    public List<FormationDTO> getFormationsByCategoryAndEntreprise(String categoryName, String entreprise) {
        try {
            List<Formation> formations = formationRepository.findByCategoryNameAndEntreprise(categoryName, entreprise);
            return formations.stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("Failed to retrieve formations", e);
        }
    }


    @Transactional
    public List<FormationDTO> getAllFormations() {
        try {
            List<Formation> formations = formationRepository.findAll();
            return formations.stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("Failed to retrieve formations", e);
        }
    }
    public byte[] getVideoContentById(Long formationId) {
        Optional<Formation> optionalFormation = formationRepository.findById(formationId);
        if (optionalFormation.isPresent()) {
            return optionalFormation.get().getContent();
        } else {
            throw new FormationNotFoundException("Formation not found with ID: " + formationId);
        }
    }


    @Transactional
    public List<FormationDTO> getAllFormationsByEnterprise(String entrepriseNom) {
        try {
            List<Formation> formations = formationRepository.findByEntreprise(entrepriseNom);
            return formations.stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("Failed to retrieve formations by enterprise", e);
        }
    }

    @Transactional
    public Optional<Formation> getFormationsByID(long id) {
        try {
            return formationRepository.findById(id);
        } catch (Exception e) {
            throw new ServiceException("Failed to retrieve formations by id: " + id, e);
        }
    }




    private FormationDTO mapToDTO(Formation formation) {
        FormationDTO dto = new FormationDTO();
        dto.setId(formation.getId());
        dto.setTitre(formation.getTitre());
        dto.setDuree(formation.getDuree());
        dto.setLangue(formation.getLangue());
        dto.setNiveau(formation.getNiveau());
        dto.setFileName(formation.getFileName());
        dto.setParticipants(formation.getParticipants());
        dto.setDescription(formation.getDescription());
        dto.setFormateur(formation.getFormateur());
        dto.setEntreprise(formation.getEntreprise());
       dto.setDateAjouter(formation.getDateAjouter());
       dto.setCategories(formation.getCategories());
        dto.setStatut(formation.getStatut());
        return dto;
    }
    @Autowired
    private CategoryRepo categoryRepository;
    public void initializeCategories() {
        String[] categoryNames = {"IT", "IoT", "Marketing", "Blockchain", "Finance", "Comptabilité Auto"};
        for (String name : categoryNames) {
            if (categoryRepository.findByName(name) == null) {
                Category category = new Category();
                category.setName(name);
                categoryRepository.save(category);
            }
        }
    }

}