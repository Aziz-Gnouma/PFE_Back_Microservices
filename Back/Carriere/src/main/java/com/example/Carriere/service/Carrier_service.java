package com.example.Carriere.service;

import com.example.Carriere.entity.Avancement;
import com.example.Carriere.entity.Carriere;
import com.example.Carriere.entity.Structure;
import com.example.Carriere.entity.Titularisation;
import com.example.Carriere.repository.Avancement_repo;
import com.example.Carriere.repository.Carriere_repo;
import com.example.Carriere.repository.Structure_repo;
import com.example.Carriere.repository.Titularisation_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Carrier_service {
    @Autowired
    private  Carriere_repo Carriere_repo;
    @Autowired
    private Titularisation_repo Titularisation_repo;
    @Autowired
    private Structure_repo Structure_repo;
    @Autowired
    private Avancement_repo  Avancement_repo;


    public List<Object[]> getDataByMatricule(String matricule) {
        // Fetching data separately from each repository
        List<Titularisation> titularisations = Titularisation_repo.findByMatricule(matricule);
        List<Structure> structures = Structure_repo.findByMatricule(matricule);
        List<Avancement> avancements = Avancement_repo.findByMatricule(matricule);

        // Combine the fetched data into a list of arrays
        // Assuming all three lists have the same size
        List<Object[]> combinedData = new ArrayList<>();
        for (int i = 0; i < titularisations.size(); i++) {
            combinedData.add(new Object[]{titularisations.get(i), structures.get(i), avancements.get(i)});
        }

        return combinedData;
    }

    public ResponseEntity<String> createCarriere( Structure structure,Avancement avancement, Titularisation titularisation) {
        try {
            Date currentDate = new Date();

            // Save Avancement
            if (avancement != null) {
                avancement.setSituation("activite");
                if (avancement.getDateAjouter() == null) {
                    avancement.setDateAjouter(currentDate);
                }
                Avancement_repo.save(avancement);
            }

            // Save Structure
            if (structure != null) {
                structure.setSituation("activite");
                if (structure.getDateAjouter() == null) {
                    structure.setDateAjouter(currentDate);
                }
                Structure_repo.save(structure);
            }

            // Save Titularisation
            if (titularisation != null) {
                titularisation.setSituation("activite");
                if (titularisation.getDateAjouter() == null) {
                    titularisation.setDateAjouter(currentDate);
                }
                Titularisation_repo.save(titularisation);
            }

            return ResponseEntity.ok("Carriere created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error in carriere ");
        }
    }

    public List<Structure> getAllStructuresByMatricule(String matricule) {
        return Structure_repo.findByMatricule(matricule);
    }
    public List<Avancement> getAllAvancementsByMatricule(String matricule) {
        return Avancement_repo.findByMatricule(matricule);
    }
    public List<Titularisation> getAllTitularisationsByMatricule(String matricule) {
        return Titularisation_repo.findByMatricule(matricule);
    }

    public Map<String, List<?>> getCarriereByMatricule(String matricule) {
        Map<String, List<?>> result = new HashMap<>();


        List<Avancement> avancementList = Avancement_repo.findByMatriculeAndLatestDateAjouter(matricule);
        result.put("avancement", avancementList);

        List<Structure> structureList = Structure_repo.findByMatriculeAndLatestDateAjouter(matricule);
        result.put("structure", structureList);

        List<Titularisation> titularisationList = Titularisation_repo.findByMatriculeAndLatestDateAjouter(matricule);
        result.put("titularisation", titularisationList);

        return result;
    }



//    public ResponseEntity<String> create( Carriere Carriere) {
//        Optional<Carriere> existingCarriere = Carriere_repo.findById(Carriere.getId());
//        if (existingCarriere.isPresent()) {
//            throw new RuntimeException("Carriere with the provided ID already exists");
//        }
//        try {
//            Carriere_repo.save(Carriere);
//            return ResponseEntity.ok("Carriere created successfully.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error in carriere ");
//        }
//    }



    public Optional<Carriere> afficher(int id) {
        return Carriere_repo.findById(id);
    }

    public List<Carriere> getAllCarrieres() {
        return Carriere_repo.findAll();
    }

    public ResponseEntity<String> deleteCarriere(int cin) {
        try {
            Carriere_repo.deleteById(cin);
            return ResponseEntity.ok("Carriere with cin: " + cin + " deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting carriere with cin: " + cin);
        }
    }

    private boolean isNotBlank(String str) {
        return str != null && !str.isEmpty();
    }
    public ResponseEntity<String> updateCarriereById(int id, Carriere updatedCarriere) {
        // Fetch the existing Carriere by ID
        Optional<Carriere> carriereOptional = Carriere_repo.findById(id);
        if (carriereOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating Carriere with ID " + id);        }

        Carriere existingCarriere = carriereOptional.get();

        // Update fields with values from updatedCarriere
        if (updatedCarriere.getGrilleDeSalaire() != null) {
            existingCarriere.setGrilleDeSalaire(updatedCarriere.getGrilleDeSalaire());
        }
        if (updatedCarriere.getGrilleDeDattes() != null) {
            existingCarriere.setGrilleDeDattes(updatedCarriere.getGrilleDeDattes());
        }
        if (updatedCarriere.getDateCategorie() != null) {
            existingCarriere.setDateCategorie(updatedCarriere.getDateCategorie());
        }
        if (updatedCarriere.getCategorieProchaine() != null) {
            existingCarriere.setCategorieProchaine(updatedCarriere.getCategorieProchaine());
        }
        if (updatedCarriere.getEchelle() != null) {
            existingCarriere.setEchelle(updatedCarriere.getEchelle());
        }
        if (updatedCarriere.getDateEchelle() != null) {
            existingCarriere.setDateEchelle(updatedCarriere.getDateEchelle());
        }
        if (updatedCarriere.getEchelleProchaine() != null) {
            existingCarriere.setEchelleProchaine(updatedCarriere.getEchelleProchaine());
        }
        if (updatedCarriere.getEchelon() != null) {
            existingCarriere.setEchelon(updatedCarriere.getEchelon());
        }
        if (updatedCarriere.getEchelonDeDates() != null) {
            existingCarriere.setEchelonDeDates(updatedCarriere.getEchelonDeDates());
        }
        if (updatedCarriere.getDateRabattement() != null) {
            existingCarriere.setDateRabattement(updatedCarriere.getDateRabattement());
        }
        if (updatedCarriere.getEchelonProchain() != null) {
            existingCarriere.setEchelonProchain(updatedCarriere.getEchelonProchain());
        }
        if (updatedCarriere.getCollege() != null) {
            existingCarriere.setCollege(updatedCarriere.getCollege());
        }
        if (updatedCarriere.getDateCollege() != null) {
            existingCarriere.setDateCollege(updatedCarriere.getDateCollege());
        }
        if (updatedCarriere.getFonction() != null) {
            existingCarriere.setFonction(updatedCarriere.getFonction());
        }
        if (updatedCarriere.getDateFonction() != null) {
            existingCarriere.setDateFonction(updatedCarriere.getDateFonction());
        }
        if (updatedCarriere.getGrade() != null) {
            existingCarriere.setGrade(updatedCarriere.getGrade());
        }
        if (updatedCarriere.getNoteDeDate() != null) {
            existingCarriere.setNoteDeDate(updatedCarriere.getNoteDeDate());
        }
        if (updatedCarriere.getDateEssai() != null) {
            existingCarriere.setDateEssai(updatedCarriere.getDateEssai());
        }
        if (updatedCarriere.getTitularisation() != null) {
            existingCarriere.setTitularisation(updatedCarriere.getTitularisation());
        }
        if (updatedCarriere.getGradeProchain() != null) {
            existingCarriere.setGradeProchain(updatedCarriere.getGradeProchain());
        }
        if (updatedCarriere.getNatureDuDiplome() != null) {
            existingCarriere.setNatureDuDiplome(updatedCarriere.getNatureDuDiplome());
        }
        if (updatedCarriere.getMotifDeDepart() != null) {
            existingCarriere.setMotifDeDepart(updatedCarriere.getMotifDeDepart());
        }
        if (updatedCarriere.getRetraitePrevue() != null) {
            existingCarriere.setRetraitePrevue(updatedCarriere.getRetraitePrevue());
        }
        if (updatedCarriere.getDateDepart() != null) {
            existingCarriere.setDateDepart(updatedCarriere.getDateDepart());
        }
        // Save the updated Carriere
        try {
            Carriere_repo.save(existingCarriere);
            return ResponseEntity.ok("Carriere with ID " + id + " updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating Carriere with ID " + id);
        }
    }
}
