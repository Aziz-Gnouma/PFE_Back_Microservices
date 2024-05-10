package com.Salaire.Salaire.entity;

public class IRPPRequest {
    private String natureRevenuImposable;
    private int parentsEnCharge;
    private double autresDeductions;
    private double montantMensuel;
    private boolean chefDeFamille;
    private int enfants;
    private int enfantsSansBourse;
    private int enfantsInfirmes;
    private String matricule;


    private boolean SecteurPrive ;

    public boolean isSecteurPrive() {
        return SecteurPrive;
    }

    public void setSecteurPrive(boolean secteurPrive) {
        SecteurPrive = secteurPrive;
    }

    private String NomEntreprise ;
    private String AdresseEntreprise ;
    private String AffiliationCss ;
    private String CinEmploye ;
    private String NomEmploye ;
    private String Adresse ;
    private String NCss;
    private String SituationFamiliale;
    private String Fonction;


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

//    public IRPPRequest(String natureRevenuImposable, int parentsEnCharge, double autresDeductions, double montantMensuel,
//                       boolean chefDeFamille, int enfants, int enfantsSansBourse, int enfantsInfirmes) {
//        this.natureRevenuImposable = natureRevenuImposable;
//        this.parentsEnCharge = parentsEnCharge;
//        this.autresDeductions = autresDeductions;
//        this.montantMensuel = montantMensuel;
//        this.chefDeFamille = chefDeFamille;
//        this.enfants = enfants;
//        this.enfantsSansBourse = enfantsSansBourse;
//        this.enfantsInfirmes = enfantsInfirmes;
//    }

    public String getNatureRevenuImposable() {
        return natureRevenuImposable;
    }

    public void setNatureRevenuImposable(String natureRevenuImposable) {
        this.natureRevenuImposable = natureRevenuImposable;
    }

    public int getParentsEnCharge() {
        return parentsEnCharge;
    }

    public void setParentsEnCharge(int parentsEnCharge) {
        this.parentsEnCharge = parentsEnCharge;
    }

    public double getAutresDeductions() {
        return autresDeductions;
    }

    public void setAutresDeductions(double autresDeductions) {
        this.autresDeductions = autresDeductions;
    }

    public double getMontantMensuel() {
        return montantMensuel;
    }

    public void setMontantMensuel(double montantMensuel) {
        this.montantMensuel = montantMensuel;
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

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
}
