package com.example.authentication.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Entreprise {

    @Id
    private String entrepriseName;
    private String entrepriseDescription;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateInscriptionRegistreCommerce;
    private String affiliationCaisseSociale;
    private String numeroSecuriteSocial;
    private String numeroCompteBancaire;
    private String assurance;
    private String emailAssurance;
    private String telAssurance;
    private String numeroAffiliationAssurance;

    public Date getDateInscriptionRegistreCommerce() {
        return dateInscriptionRegistreCommerce;
    }

    public void setDateInscriptionRegistreCommerce(Date dateInscriptionRegistreCommerce) {
        this.dateInscriptionRegistreCommerce = dateInscriptionRegistreCommerce;
    }

    public String getAffiliationCaisseSociale() {
        return affiliationCaisseSociale;
    }

    public void setAffiliationCaisseSociale(String affiliationCaisseSociale) {
        this.affiliationCaisseSociale = affiliationCaisseSociale;
    }

    public String getNumeroSecuriteSocial() {
        return numeroSecuriteSocial;
    }

    public void setNumeroSecuriteSocial(String numeroSecuriteSocial) {
        this.numeroSecuriteSocial = numeroSecuriteSocial;
    }

    public String getNumeroCompteBancaire() {
        return numeroCompteBancaire;
    }

    public void setNumeroCompteBancaire(String numeroCompteBancaire) {
        this.numeroCompteBancaire = numeroCompteBancaire;
    }

    public String getAssurance() {
        return assurance;
    }

    public void setAssurance(String assurance) {
        this.assurance = assurance;
    }

    public String getEmailAssurance() {
        return emailAssurance;
    }

    public void setEmailAssurance(String emailAssurance) {
        this.emailAssurance = emailAssurance;
    }

    public String getTelAssurance() {
        return telAssurance;
    }

    public void setTelAssurance(String telAssurance) {
        this.telAssurance = telAssurance;
    }

    public String getNumeroAffiliationAssurance() {
        return numeroAffiliationAssurance;
    }

    public void setNumeroAffiliationAssurance(String numeroAffiliationAssurance) {
        this.numeroAffiliationAssurance = numeroAffiliationAssurance;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCode_Postal() {
        return code_Postal;
    }

    public void setCode_Postal(String code_Postal) {
        this.code_Postal = code_Postal;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getTel_Entreprise() {
        return tel_Entreprise;
    }

    public void setTel_Entreprise(String tel_Entreprise) {
        this.tel_Entreprise = tel_Entreprise;
    }




    public Date getDate_Creation_Entreprise() {
        return date_Creation_Entreprise;
    }

    public void setDate_Creation_Entreprise(Date date_Creation_Entreprise) {
        this.date_Creation_Entreprise = date_Creation_Entreprise;
    }

    public String getDomaine_Activite() {
        return domaine_Activite;
    }

    public void setDomaine_Activite(String domaine_Activite) {
        this.domaine_Activite = domaine_Activite;
    }

    public int getNb_Employees() {
        return nb_Employees;
    }

    public void setNb_Employees(int nb_Employees) {
        this.nb_Employees = nb_Employees;
    }

    public String getSite_Web() {
        return site_Web;
    }

    public void setSite_Web(String site_Web) {
        this.site_Web = site_Web;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getAdresse_Entreprise() {
        return adresse_Entreprise;
    }

    public void setAdresse_Entreprise(String adresse_Entreprise) {
        this.adresse_Entreprise = adresse_Entreprise;
    }

    private String adresse_Entreprise;
    private String ville;
    private String code_Postal;
    private String pays;
    private String tel_Entreprise;



    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumFiscale() {
        return numFiscale;
    }

    public void setNumFiscale(String numFiscale) {
        this.numFiscale = numFiscale;
    }

    private String numFiscale;
    @JsonFormat(pattern="dd-MM-yyyy")

    private Date date_Creation_Entreprise;
    private String domaine_Activite;
    private int nb_Employees;
    private String site_Web;
    private int etat;




    public String getEntrepriseName() {
        return entrepriseName;
    }

    public void setEntrepriseName(String entrepriseName) {
        this.entrepriseName = entrepriseName;
    }

    public String getEntrepriseDescription() {
        return entrepriseDescription;
    }

    public void setEntrepriseDescription(String entrepriseDescription) {
        this.entrepriseDescription = entrepriseDescription;
    }
}
