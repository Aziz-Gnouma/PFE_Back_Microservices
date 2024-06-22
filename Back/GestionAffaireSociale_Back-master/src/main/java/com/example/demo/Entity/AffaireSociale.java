package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Entity
public class AffaireSociale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String situationFamiliale;
    private String matriculeConjoint;
    private String prenomConjoint;
    private int nbEnfants;
    private int nbEnfantsImposables;
    private boolean chefDeFamille;
    private boolean salaireUnique;
    private boolean allocFamiliale;
    private String securiteSociale;
    private String nomAssurance;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date affiliationLe;
    private boolean exonere;
    private boolean affiliationRegime;
    private boolean affiliationAssurance;
    private boolean affiliationMutuelle;

    public String getSituationFamiliale() {
        return situationFamiliale;
    }

    public void setSituationFamiliale(String situationFamiliale) {
        this.situationFamiliale = situationFamiliale;
    }

    public String getMatriculeConjoint() {
        return matriculeConjoint;
    }

    public void setMatriculeConjoint(String matriculeConjoint) {
        this.matriculeConjoint = matriculeConjoint;
    }

    public String getPrenomConjoint() {
        return prenomConjoint;
    }

    public void setPrenomConjoint(String prenomConjoint) {
        this.prenomConjoint = prenomConjoint;
    }

    public int getNbEnfants() {
        return nbEnfants;
    }

    public void setNbEnfants(int nbEnfants) {
        this.nbEnfants = nbEnfants;
    }

    public int getNbEnfantsImposables() {
        return nbEnfantsImposables;
    }

    public void setNbEnfantsImposables(int nbEnfantsImposables) {
        this.nbEnfantsImposables = nbEnfantsImposables;
    }

    public boolean isChefDeFamille() {
        return chefDeFamille;
    }

    public void setChefDeFamille(boolean chefDeFamille) {
        this.chefDeFamille = chefDeFamille;
    }

    public boolean isSalaireUnique() {
        return salaireUnique;
    }

    public void setSalaireUnique(boolean salaireUnique) {
        this.salaireUnique = salaireUnique;
    }

    public boolean isAllocFamiliale() {
        return allocFamiliale;
    }

    public void setAllocFamiliale(boolean allocFamiliale) {
        this.allocFamiliale = allocFamiliale;
    }




    public boolean isExonere() {
        return exonere;
    }

    public void setExonere(boolean exonere) {
        this.exonere = exonere;
    }

    public boolean isAffiliationRegime() {
        return affiliationRegime;
    }

    public void setAffiliationRegime(boolean affiliationRegime) {
        this.affiliationRegime = affiliationRegime;
    }

    public boolean isAffiliationAssurance() {
        return affiliationAssurance;
    }

    public void setAffiliationAssurance(boolean affiliationAssurance) {
        this.affiliationAssurance = affiliationAssurance;
    }

    public boolean isAffiliationMutuelle() {
        return affiliationMutuelle;
    }

    public void setAffiliationMutuelle(boolean affiliationMutuelle) {
        this.affiliationMutuelle = affiliationMutuelle;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Date getAffiliationLe() {
        return affiliationLe;
    }

    public void setAffiliationLe(Date affiliationLe) {
        this.affiliationLe = affiliationLe;
    }

    public String getSecuriteSociale() {
        return securiteSociale;
    }

    public void setSecuriteSociale(String securiteSociale) {
        this.securiteSociale = securiteSociale;
    }

    public String getNomAssurance() {
        return nomAssurance;
    }

    public void setNomAssurance(String nomAssurance) {
        this.nomAssurance = nomAssurance;
    }


    // Projection
    @Projection(name = "fullAffaireSociale", types = AffaireSociale.class)
    interface AffaireSocialeProjection {
        String getSituationFamiliale();
        String getMatriculeConjoint();
    }
}
