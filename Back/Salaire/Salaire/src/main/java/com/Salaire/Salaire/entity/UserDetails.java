package com.Salaire.Salaire.entity;

import jakarta.persistence.Column;

import java.util.Date;

public class UserDetails {
    private double montantMensuel;
    private double cotisation;
    private double salaireImposable;
    private double irppMensuel;
    private double cssMensuel;
    private double salaireNet;
    private Date DateAjouter;
    @Column(name = "matricule")
    private String matricule;
    private boolean SecteurPrive ;
    private String NomEntreprise ;
    private String AdresseEntreprise ;
    private String AffiliationCss ;
    private String CinEmploye ;
    private String NomEmploye ;
    private String Adresse ;
    private String NCss;
    private String SituationFamiliale;
    private String Fonction;

    // Constructor
    public UserDetails() {
    }

    // Getters and setters

    public double getMontantMensuel() {
        return montantMensuel;
    }

    public void setMontantMensuel(double montantMensuel) {
        this.montantMensuel = montantMensuel;
    }

    public double getCotisation() {
        return cotisation;
    }

    public void setCotisation(double cotisation) {
        this.cotisation = cotisation;
    }

    public double getSalaireImposable() {
        return salaireImposable;
    }

    public void setSalaireImposable(double salaireImposable) {
        this.salaireImposable = salaireImposable;
    }

    public double getIrppMensuel() {
        return irppMensuel;
    }

    public void setIrppMensuel(double irppMensuel) {
        this.irppMensuel = irppMensuel;
    }

    public double getCssMensuel() {
        return cssMensuel;
    }

    public void setCssMensuel(double cssMensuel) {
        this.cssMensuel = cssMensuel;
    }

    public double getSalaireNet() {
        return salaireNet;
    }

    public void setSalaireNet(double salaireNet) {
        this.salaireNet = salaireNet;
    }

    public Date getDateAjouter() {
        return DateAjouter;
    }

    public void setDateAjouter(Date dateAjouter) {
        DateAjouter = dateAjouter;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public boolean isSecteurPrive() {
        return SecteurPrive;
    }

    public void setSecteurPrive(boolean secteurPrive) {
        SecteurPrive = secteurPrive;
    }

    public String getNomEntreprise() {
        return NomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        NomEntreprise = nomEntreprise;
    }

    public String getAdresseEntreprise() {
        return AdresseEntreprise;
    }

    public void setAdresseEntreprise(String adresseEntreprise) {
        AdresseEntreprise = adresseEntreprise;
    }

    public String getAffiliationCss() {
        return AffiliationCss;
    }

    public void setAffiliationCss(String affiliationCss) {
        AffiliationCss = affiliationCss;
    }

    public String getCinEmploye() {
        return CinEmploye;
    }

    public void setCinEmploye(String cinEmploye) {
        CinEmploye = cinEmploye;
    }

    public String getNomEmploye() {
        return NomEmploye;
    }

    public void setNomEmploye(String nomEmploye) {
        NomEmploye = nomEmploye;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getNCss() {
        return NCss;
    }

    public void setNCss(String NCss) {
        this.NCss = NCss;
    }

    public String getSituationFamiliale() {
        return SituationFamiliale;
    }

    public void setSituationFamiliale(String situationFamiliale) {
        SituationFamiliale = situationFamiliale;
    }

    public String getFonction() {
        return Fonction;
    }

    public void setFonction(String fonction) {
        Fonction = fonction;
    }
}
