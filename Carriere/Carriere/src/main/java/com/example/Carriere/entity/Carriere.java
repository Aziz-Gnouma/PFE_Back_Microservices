package com.example.Carriere.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Carriere {


    @Id
    private Integer Cin;

    private String salaire;
    private String categorie;
    private Date dateEntree;
    private String fonction;
    private String grade;
    private String natureDiplome;
    private String niveauEducation;
    private String languesMaitrisees;
    private String experienceProfessionnelle;
    private String competencesSpecialisees;
    private Date dateDepart;

    public Integer getCin() {
        return Cin;
    }

    public void setCin(Integer cin) {
        Cin = cin;
    }




    public String getSalaire() {
        return salaire;
    }

    public void setSalaire(String salaire) {
        this.salaire = salaire;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Date getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(Date dateEntree) {
        this.dateEntree = dateEntree;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getNatureDiplome() {
        return natureDiplome;
    }

    public void setNatureDiplome(String natureDiplome) {
        this.natureDiplome = natureDiplome;
    }

    public String getNiveauEducation() {
        return niveauEducation;
    }

    public void setNiveauEducation(String niveauEducation) {
        this.niveauEducation = niveauEducation;
    }

    public String getLanguesMaitrisees() {
        return languesMaitrisees;
    }

    public void setLanguesMaitrisees(String languesMaitrisees) {
        this.languesMaitrisees = languesMaitrisees;
    }

    public String getExperienceProfessionnelle() {
        return experienceProfessionnelle;
    }

    public void setExperienceProfessionnelle(String experienceProfessionnelle) {
        this.experienceProfessionnelle = experienceProfessionnelle;
    }

    public String getCompetencesSpecialisees() {
        return competencesSpecialisees;
    }

    public void setCompetencesSpecialisees(String competencesSpecialisees) {
        this.competencesSpecialisees = competencesSpecialisees;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }
}
