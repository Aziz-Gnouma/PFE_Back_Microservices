package com.example.congesabs.Service;


import com.example.congesabs.entity.Absence;
import com.example.congesabs.repository.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbsenceService {

    @Autowired
    private AbsenceRepository absenceRepository;

    public Absence createAbsence(Absence absence) {
        return absenceRepository.save(absence);
    }

    public Absence updateAbsence(Long id, Absence absence) {
        absence.setId(id);
        return absenceRepository.save(absence);
    }

    public Absence getAbsenceById(Long id) {
        Optional<Absence> optional = absenceRepository.findById(id);
        return optional.orElse(null);
    }

    public List<Absence> getAllAbsences() {
        return absenceRepository.findAll();
    }

    public void deleteAbsence(Long id) {
        absenceRepository.deleteById(id);
    }

    public Absence validateAbsence(Long id) {
        Absence absence = getAbsenceById(id);
        absence.setIsValidated(true);
        return absenceRepository.save(absence);
    }
    public List<Absence> getPendingAbsences() {
        return absenceRepository.findByStatus(AbsenceStatus.PENDING);
    }
    public Absence rejectAbsence(Long id) {
        Absence absence = getAbsenceById(id);
        absence.setIsValidated(false);
        return absenceRepository.save(absence);
    }
}