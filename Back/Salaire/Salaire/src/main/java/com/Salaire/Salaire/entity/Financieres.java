package com.Salaire.Salaire.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Financieres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Financieres_ID;

    private String matricule;
    private String grilleSalaire;
    private double salaireBase;
    private String modePaiement;
    private String numeroCompte;
    private Date DateAjouter;

    public Date getDateAjouter() {
        return DateAjouter;
    }

    public void setDateAjouter(Date dateAjouter) {
        DateAjouter = dateAjouter;
    }

    @ManyToOne
    @JoinColumn(name = "bank_name" ,referencedColumnName = "name")
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "ageney_name",referencedColumnName = "name")
    private Agence agency;
    private double montantAssurance;
    private double montantMutuelle;

    public Long getFinancieres_ID() {
        return Financieres_ID;
    }

    public void setFinancieres_ID(Long financieres_ID) {
        Financieres_ID = financieres_ID;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

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

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Agence getAgency() {
        return agency;
    }

    public void setAgency(Agence agency) {
        this.agency = agency;
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

