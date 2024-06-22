package com.example.Carriere.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
@JsonIgnoreProperties(ignoreUnknown = true)

@Entity
public class Structure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_Structures;
    private String matricule;
    private String fonction;
    private Date dateFonction;
    private String structureAttache;
    private Date dateAffectation;
    private String situation;
    private Date DateAjouter;

    public Date getDateAjouter() {
        return DateAjouter;
    }

    public void setDateAjouter(Date dateAjouter) {
        DateAjouter = dateAjouter;
    }
    public Integer getId_Structures() {
        return Id_Structures;
    }

    public void setId_Structures(Integer id_Structures) {
        Id_Structures = id_Structures;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public Date getDateFonction() {
        return dateFonction;
    }

    public void setDateFonction(Date dateFonction) {
        this.dateFonction = dateFonction;
    }

    public String getStructureAttache() {
        return structureAttache;
    }

    public void setStructureAttache(String structureAttache) {
        this.structureAttache = structureAttache;
    }

    public Date getDateAffectation() {
        return dateAffectation;
    }

    public void setDateAffectation(Date dateAffectation) {
        this.dateAffectation = dateAffectation;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }
}
