package com.Pfe.Formation.DAO;

import com.Pfe.Formation.entity.Category;

import java.util.Date;
import java.util.Set;

public class FormationDTO {
    private long Id;

    private String titre;
    private String duree;
    private String langue;
    private String niveau;
    private String fileName;
    private String description;
    private String formateur;
    private String entreprise;
    private String statut;
    private Date dateAjouter;
    private int participants;
    private Set<Category> categories;
    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormateur() {
        return formateur;
    }

    public void setFormateur(String formateur) {
        this.formateur = formateur;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public Date getDateAjouter() {
        return dateAjouter;
    }

    public void setDateAjouter(Date dateAjouter) {
        this.dateAjouter = dateAjouter;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
