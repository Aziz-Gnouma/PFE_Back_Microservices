package com.example.congesabs.controlle;


import com.example.congesabs.Service.AbsenceService;
import com.example.congesabs.entity.Absence;
import com.example.congesabs.entity.ValidAbsence;
import com.example.congesabs.repository.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/absences")
public class AbsenceController {

    @Autowired
    private AbsenceService absenceService;
    @Autowired
    private ValidAbsence validAbsence;
    @Autowired
private AbsenceRepository absenceRepository;
    @PostMapping
    public Absence createAbsence(@RequestBody Absence absence) {
        return absenceService.createAbsence(absence);
    }


    @PutMapping("/{id}")
    public Absence updateAbsence(@PathVariable Long id, @RequestBody Absence absence) {
        return absenceService.updateAbsence(id, absence);
    }

    @GetMapping("/{id}")
    public Absence getAbsenceById(@PathVariable Long id) {
        return absenceService.getAbsenceById(id);
    }

    @GetMapping
    public List<Absence> getAllAbsences() {
        return absenceService.getAllAbsences();
    }

    @DeleteMapping("/{id}")
    public void deleteAbsence(@PathVariable Long id) {
        absenceService.deleteAbsence(id);
    }

    @PostMapping("/validate/{id}")
    public Absence validateAbsence(@PathVariable Long id) {
        return absenceService.validateAbsence(id);
    }

    @PostMapping("/reject/{id}")
    public Absence rejectAbsence(@PathVariable Long id) {
        return absenceService.rejectAbsence(id);
    }
    @GetMapping("/pending")
    public List<Absence> getPendingAbsences() {
        return absenceRepository.findAll();
    }
    @GetMapping("/absences")
    public ResponseEntity<List<Absence>> getAllAbsencesByMatricule(@RequestParam String matricule) {
        List<Absence> absencesByMatricule = absenceRepository.findByMatricule(matricule);
        return ResponseEntity.ok(absencesByMatricule);
    }

}