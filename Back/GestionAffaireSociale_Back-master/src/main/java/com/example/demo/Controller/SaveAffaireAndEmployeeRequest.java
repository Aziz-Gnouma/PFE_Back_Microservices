package com.example.demo.Controller;

import com.example.demo.Entity.AffaireSociale;

public class SaveAffaireAndEmployeeRequest {
    private AffaireSociale affaire;
    private Object employeeData;

    public AffaireSociale getAffaire() {
        return affaire;
    }

    public void setAffaire(AffaireSociale affaire) {
        this.affaire = affaire;
    }

    public Object getEmployeeData() {
        return employeeData;
    }

    public void setEmployeeData(Object employeeData) {
        this.employeeData = employeeData;
    }

    // Getters and setters
}