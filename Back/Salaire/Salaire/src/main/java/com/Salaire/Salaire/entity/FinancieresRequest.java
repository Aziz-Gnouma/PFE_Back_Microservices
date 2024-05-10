package com.Salaire.Salaire.entity;

public class FinancieresRequest {
    private String grilleSalaire;
    private double salaireBase;
    private String modePaiement;
    private String numeroCompte;
    private String bankName;
    private String agencyName;
    private double montantAssurance;
    private double montantMutuelle;
    private String Matricule;

    public String getMatricule() {
        return Matricule;
    }

    public void setMatricule(String matricule) {
        Matricule = matricule;
    }

    // Getters and setters
    public String getGrilleSalaire() {
        return grilleSalaire;
    }

    public void setGrilleSalaire(String grilleSalaire) {
        this.grilleSalaire = grilleSalaire;
    }

    public double getSalaireBase() {
        return salaireBase;
    }

    public void setSalaireBase(double salaireBase) {
        this.salaireBase = salaireBase;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public double getMontantAssurance() {
        return montantAssurance;
    }

    public void setMontantAssurance(double montantAssurance) {
        this.montantAssurance = montantAssurance;
    }

    public double getMontantMutuelle() {
        return montantMutuelle;
    }

    public void setMontantMutuelle(double montantMutuelle) {
        this.montantMutuelle = montantMutuelle;
    }
}
