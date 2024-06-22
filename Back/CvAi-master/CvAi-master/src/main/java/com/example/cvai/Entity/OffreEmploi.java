package com.example.cvai.Entity;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class OffreEmploi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String nomEntreprise;
    private String lieu;
    private Double salaire;
    private String typeDePoste;
    private String detailsEmploi;
    private String tempsDeTravail;
    private String descriptionPoste;
    @ManyToMany(mappedBy = "offreEmplois")
    private List<CV> cvs;


    public List<CV> getCvs() {
        return cvs;
    }

    public void setCvs(List<CV> cvs) {
        this.cvs = cvs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Double getSalaire() {
        return salaire;
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }

    public String getTypeDePoste() {
        return typeDePoste;
    }

    public void setTypeDePoste(String typeDePoste) {
        this.typeDePoste = typeDePoste;
    }

    public String getDetailsEmploi() {
        return detailsEmploi;
    }

    public void setDetailsEmploi(String detailsEmploi) {
        this.detailsEmploi = detailsEmploi;
    }

    public String getTempsDeTravail() {
        return tempsDeTravail;
    }

    public void setTempsDeTravail(String tempsDeTravail) {
        this.tempsDeTravail = tempsDeTravail;
    }

    public String getDescriptionPoste() {
        return descriptionPoste;
    }

    public void setDescriptionPoste(String descriptionPoste) {
        this.descriptionPoste = descriptionPoste;
    }
}
