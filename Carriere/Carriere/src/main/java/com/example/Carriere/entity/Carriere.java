package com.example.Carriere.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Carriere {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer Cin;

    private String grilleDeSalaire;
    private String grilleDeDattes;
    private Date dateCategorie;
    private String categorieProchaine;
    private String echelle;
    private String dateEchelle;
    private String echelleProchaine;
    private String echelon;
    private String echelonDeDates;
    private Date dateRabattement;
    private String echelonProchain;
    private String college;
    private Date dateCollege;
    private String fonction;
    private Date dateFonction;
    private String grade;
    private String noteDeDate;
    private Date dateEssai;
    private String titularisation;
    private String gradeProchain;
    private String natureDuDiplome;
    private String motifDeDepart;
    private String retraitePrevue;
    private Date dateDepart;

    public Integer getCin() {
        return Cin;
    }

    public void setCin(Integer cin) {
        Cin = cin;
    }

    public String getGrilleDeSalaire() {
        return grilleDeSalaire;
    }

    public void setGrilleDeSalaire(String grilleDeSalaire) {
        this.grilleDeSalaire = grilleDeSalaire;
    }

    public String getGrilleDeDattes() {
        return grilleDeDattes;
    }

    public void setGrilleDeDattes(String grilleDeDattes) {
        this.grilleDeDattes = grilleDeDattes;
    }

    public Date getDateCategorie() {
        return dateCategorie;
    }

    public void setDateCategorie(Date dateCategorie) {
        this.dateCategorie = dateCategorie;
    }

    public String getCategorieProchaine() {
        return categorieProchaine;
    }

    public void setCategorieProchaine(String categorieProchaine) {
        this.categorieProchaine = categorieProchaine;
    }

    public String getEchelle() {
        return echelle;
    }

    public void setEchelle(String echelle) {
        this.echelle = echelle;
    }

    public String getDateEchelle() {
        return dateEchelle;
    }

    public void setDateEchelle(String dateEchelle) {
        this.dateEchelle = dateEchelle;
    }

    public String getEchelleProchaine() {
        return echelleProchaine;
    }

    public void setEchelleProchaine(String echelleProchaine) {
        this.echelleProchaine = echelleProchaine;
    }

    public String getEchelon() {
        return echelon;
    }

    public void setEchelon(String echelon) {
        this.echelon = echelon;
    }

    public String getEchelonDeDates() {
        return echelonDeDates;
    }

    public void setEchelonDeDates(String echelonDeDates) {
        this.echelonDeDates = echelonDeDates;
    }

    public Date getDateRabattement() {
        return dateRabattement;
    }

    public void setDateRabattement(Date dateRabattement) {
        this.dateRabattement = dateRabattement;
    }

    public String getEchelonProchain() {
        return echelonProchain;
    }

    public void setEchelonProchain(String echelonProchain) {
        this.echelonProchain = echelonProchain;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Date getDateCollege() {
        return dateCollege;
    }

    public void setDateCollege(Date dateCollege) {
        this.dateCollege = dateCollege;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getNoteDeDate() {
        return noteDeDate;
    }

    public void setNoteDeDate(String noteDeDate) {
        this.noteDeDate = noteDeDate;
    }

    public Date getDateEssai() {
        return dateEssai;
    }

    public void setDateEssai(Date dateEssai) {
        this.dateEssai = dateEssai;
    }

    public String getTitularisation() {
        return titularisation;
    }

    public void setTitularisation(String titularisation) {
        this.titularisation = titularisation;
    }

    public String getGradeProchain() {
        return gradeProchain;
    }

    public void setGradeProchain(String gradeProchain) {
        this.gradeProchain = gradeProchain;
    }

    public String getNatureDuDiplome() {
        return natureDuDiplome;
    }

    public void setNatureDuDiplome(String natureDuDiplome) {
        this.natureDuDiplome = natureDuDiplome;
    }

    public String getMotifDeDepart() {
        return motifDeDepart;
    }

    public void setMotifDeDepart(String motifDeDepart) {
        this.motifDeDepart = motifDeDepart;
    }

    public String getRetraitePrevue() {
        return retraitePrevue;
    }

    public void setRetraitePrevue(String retraitePrevue) {
        this.retraitePrevue = retraitePrevue;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }
}
