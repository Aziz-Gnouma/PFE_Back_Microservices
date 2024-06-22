package com.example.congesabs.entity;

import com.example.congesabs.entity.Absence;
import org.springframework.stereotype.Component;

@Component
public class ValidAbsence {

    public boolean isValid(Absence absence) {
        return absence.getStartDate() != null &&
                absence.getReason() != null && !absence.getReason().isEmpty();
    }
}
