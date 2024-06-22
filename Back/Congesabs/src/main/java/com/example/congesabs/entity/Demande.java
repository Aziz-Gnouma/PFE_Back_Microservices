package com.example.congesabs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Date dateEnvoi;
    @NotNull
    @Future
    @CumulativeConges(otherFieldName = "dateDebut")
    private Date dateDebut;
    @NotNull
    @Future
    @CumulativeConges(otherFieldName = "dateFin")

    private Date dateFin;
    private boolean cumulative;
    private String matricule;
    private String reason;


    public boolean isCumulative() {
        return cumulative;
    }
    private String status;
    private int nombreJoursDemandes;
    private int daysRemaining;


    public void setCumulative(boolean cumulative) {
        this.cumulative = cumulative;
    }
    // Getters
    public Long getId() {
        return id;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }


    public int getNombreJoursDemandes() {
        return nombreJoursDemandes;
    }

    public void setNombreJoursDemandes(int nombreJoursDemandes) {
        this.nombreJoursDemandes = nombreJoursDemandes;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDaysRemaining() {
        return daysRemaining;
    }

    public void setDaysRemaining(int daysRemaining) {
        this.daysRemaining = daysRemaining;
    }
}


