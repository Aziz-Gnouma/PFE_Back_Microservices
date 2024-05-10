package com.Salaire.Salaire.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class salaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double montantMensuel;
    private double css;
    private String natureRevenuImposable;
    private boolean parentsEnCharge;
    private double autresDeductions;
    private boolean chefDeFamille;
    private int enfants;
    private int enfantsSansBourse;
    private int enfantsInfirmes;
    private Date DateSalaire;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getMontantMensuel() {
        return montantMensuel;
    }

    public void setMontantMensuel(double montantMensuel) {
        this.montantMensuel = montantMensuel;
    }

    public double getCss() {
        return css;
    }

    public void setCss(double css) {
        this.css = css;
    }

    public String getNatureRevenuImposable() {
        return natureRevenuImposable;
    }

    public void setNatureRevenuImposable(String natureRevenuImposable) {
        this.natureRevenuImposable = natureRevenuImposable;
    }

    public boolean isParentsEnCharge() {
        return parentsEnCharge;
    }

    public void setParentsEnCharge(boolean parentsEnCharge) {
        this.parentsEnCharge = parentsEnCharge;
    }

    public double getAutresDeductions() {
        return autresDeductions;
    }

    public void setAutresDeductions(double autresDeductions) {
        this.autresDeductions = autresDeductions;
    }

    public boolean isChefDeFamille() {
        return chefDeFamille;
    }

    public void setChefDeFamille(boolean chefDeFamille) {
        this.chefDeFamille = chefDeFamille;
    }

    public int getEnfants() {
        return enfants;
    }

    public void setEnfants(int enfants) {
        this.enfants = enfants;
    }

    public int getEnfantsSansBourse() {
        return enfantsSansBourse;
    }

    public void setEnfantsSansBourse(int enfantsSansBourse) {
        this.enfantsSansBourse = enfantsSansBourse;
    }

    public int getEnfantsInfirmes() {
        return enfantsInfirmes;
    }

    public void setEnfantsInfirmes(int enfantsInfirmes) {
        this.enfantsInfirmes = enfantsInfirmes;
    }

    public Date getDateSalaire() {
        return DateSalaire;
    }

    public void setDateSalaire(Date dateSalaire) {
        DateSalaire = dateSalaire;
    }
}
