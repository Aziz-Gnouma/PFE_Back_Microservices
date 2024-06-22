package com.example.sanctionsrecompences.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer matricule;
    private String entreprise;
    private String RHMatricule;
    private String typeReward;
    private Date date;
    private String description;

    private double value;
    private String status;

    // Constructeurs
    public Reward() {
    }

    // Ajoutez un constructeur avec tous les attributs sauf l'ID


    // Méthodes Getter et Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getMatricule() {
        return matricule;
    }

    public void setMatricule(Integer matricule) {
        this.matricule = matricule;
    }


    public String getTypeReward() {
        return typeReward;
    }

    public void setTypeReward(String typeReward) {
        this.typeReward = typeReward;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getRHMatricule() {
        return RHMatricule;
    }

    public void setRHMatricule(String RHMatricule) {
        this.RHMatricule = RHMatricule;
    }
}
