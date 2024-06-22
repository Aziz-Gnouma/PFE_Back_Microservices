package com.example.demo.Controller;

import com.example.demo.Entity.AffaireSociale;

public class SaveAffaireAndEmployeeResponse {
    private AffaireSociale savedAffaire;
    private Object savedEmployeeData;

    public AffaireSociale getSavedAffaire() {
        return savedAffaire;
    }

    public void setSavedAffaire(AffaireSociale savedAffaire) {
        this.savedAffaire = savedAffaire;
    }

    public Object getSavedEmployeeData() {
        return savedEmployeeData;
    }

    public void setSavedEmployeeData(Object savedEmployeeData) {
        this.savedEmployeeData = savedEmployeeData;
    }

    // Constructor, getters, and setters
}
