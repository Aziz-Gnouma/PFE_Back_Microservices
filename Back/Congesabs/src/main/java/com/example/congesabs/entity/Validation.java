package com.example.congesabs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Entity
public class Validation {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateValidationRd;
    private Date dateValidationPr;
    private Long idDemande;
    private Long idEmployer;
    private Long idRemplacant;
    private Long idResponsable;
    private Long idPremierResponsable;
    private char actionRd;
    private char actionPr;
    private char actionRh;
    private String avisRd;
    private String avisPr;

    // Constructors

    public Validation() {
    }

    // Getters and Setters

    public Date getDateValidationRd() {
        return dateValidationRd;
    }

    public void setDateValidationRd(Date dateValidationRd) {
        this.dateValidationRd = dateValidationRd;
    }

    public Date getDateValidationPr() {
        return dateValidationPr;
    }

    public void setDateValidationPr(Date dateValidationPr) {
        this.dateValidationPr = dateValidationPr;
    }

    public Long getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(Long idDemande) {
        this.idDemande = idDemande;
    }

    public Long getIdEmployer() {
        return idEmployer;
    }

    public void setIdEmployer(Long idEmployer) {
        this.idEmployer = idEmployer;
    }

    public Long getIdRemplacant() {
        return idRemplacant;
    }

    public void setIdRemplacant(Long idRemplacant) {
        this.idRemplacant = idRemplacant;
    }

    public Long getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(Long idResponsable) {
        this.idResponsable = idResponsable;
    }

    public Long getIdPremierResponsable() {
        return idPremierResponsable;
    }

    public void setIdPremierResponsable(Long idPremierResponsable) {
        this.idPremierResponsable = idPremierResponsable;
    }

    public char getActionRd() {
        return actionRd;
    }

    public void setActionRd(char actionRd) {
        this.actionRd = actionRd;
    }

    public char getActionPr() {
        return actionPr;
    }

    public void setActionPr(char actionPr) {
        this.actionPr = actionPr;
    }

    public char getActionRh() {
        return actionRh;
    }

    public void setActionRh(char actionRh) {
        this.actionRh = actionRh;
    }

    public String getAvisRd() {
        return avisRd;
    }

    public void setAvisRd(String avisRd) {
        this.avisRd = avisRd;
    }

    public String getAvisPr() {
        return avisPr;
    }

    public void setAvisPr(String avisPr) {
        this.avisPr = avisPr;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
