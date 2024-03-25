package com.example.Carriere.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
@Entity
public class Avancement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_Avancement;
    private String matricule;
    private String categorie;
    private String grade;
    private String classe;
    private String echelon;
    private Date dateSituation;
    private String situation;
    private Date DateAjouter;

    public Date getDateAjouter() {
        return DateAjouter;
    }

    public void setDateAjouter(Date dateAjouter) {
        DateAjouter = dateAjouter;
    }
    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public Integer getId_Avancement() {
        return Id_Avancement;
    }

    public void setId_Avancement(Integer id_Avancement) {
        Id_Avancement = id_Avancement;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getEchelon() {
        return echelon;
    }

    public void setEchelon(String echelon) {
        this.echelon = echelon;
    }

    public Date getDateSituation() {
        return dateSituation;
    }

    public void setDateSituation(Date dateSituation) {
        this.dateSituation = dateSituation;
    }
}
