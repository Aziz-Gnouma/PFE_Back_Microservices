package com.example.authentication.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Employe {



    @Id
    private String matricule;


    private Integer phoneNumber;
    private String address;

    private String userFirstName;
    private String userLastName;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateOfBirth;
    private String placeOfBirth;
    private String gender;
    private String civility;
    @Column(name = "CIN")
    private int cin;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date cinDate;
    private String nationality;
    private String codePostal;
    private String pays;
    private String email;
    private String niveauEtude;

    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateDernierDiplome;

    //**** Informations administratives  **************************************//
    private String typeContrat;
    private String typeEmployeur;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateEntree;

    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateRecrutement;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateFinEssai;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateTitularisation;
    private boolean chefFamille;
    private boolean salaireUnique;
    private boolean allocationFamille;
    private String numeroSecuriteSociale;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateAffiliation;
    private boolean exonereeSecuriteSociale;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateDebutExonereeSecuriteSociale;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateFinExonereeSecuriteSociale;
    private boolean affiliationAssuranceGroupe;
    private String nomAssurance;
    private String numeroAffiliationAssurance;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateAffiliationAssurance;
    private boolean affiliationMutuelle;
    private String nomMutuelle;
    private String numeroAffiliationMutuelle;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateAffiliationMutuelle;
    private String categorie;
    private String grade;
    private String classe;
    private String echelon;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateSituation;
    private String fonction;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateFonction;
    private String structureAttache;
    private String dateAffectation;
    private String motifDepart;
    private String dateDepart;
    private int situation;
    private String grilleSalaire;
    private double salaireDeBase;
    private String modeDePaiement;
    private String numeroCompte;
    private String nomBanque;
    private String nomAgence;
    private double montantAssurance;
    private double montantMutuelle;
private String entrepriseName ;

    public String getEntrepriseName() {
        return entrepriseName;
    }

    public void setEntrepriseName(String entrepriseName) {
        this.entrepriseName = entrepriseName;
    }
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "USER_ROLE",
//            joinColumns = {
//                    @JoinColumn(name = "USER_ID")
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "ROLE_ID")
//            }
//    )
//    private Set<Role> role;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "Employee__administrative",
//            joinColumns = {@JoinColumn(name = "Employee_ID")},
//            inverseJoinColumns = {@JoinColumn(name = "administrative_ID")}
//    )
//    private Set<administrative> administrative;



    // Getter and setter for role field
//    public Set<Role> getRole() {
//        return role;
//    }
//
//    public void setRole(Set<Role> role) {
//        this.role = role;
//    }

    // Getter and setter for entreprise field
//    public Set<administrative> getadministrative() {
//        return administrative;
//    }
//
//    public void setadministrative(Set<administrative> administrative) {
//        this.administrative = administrative;
//    }


    //**************************************************************************************************


    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCivility() {
        return civility;
    }

    public void setCivility(String civility) {
        this.civility = civility;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public Date getCinDate() {
        return cinDate;
    }

    public void setCinDate(Date cinDate) {
        this.cinDate = cinDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }



    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNiveauEtude() {
        return niveauEtude;
    }

    public void setNiveauEtude(String niveauEtude) {
        this.niveauEtude = niveauEtude;
    }

    public Date getDateDernierDiplome() {
        return dateDernierDiplome;
    }

    public void setDateDernierDiplome(Date dateDernierDiplome) {
        this.dateDernierDiplome = dateDernierDiplome;
    }

    public String getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(String typeContrat) {
        this.typeContrat = typeContrat;
    }

    public String getTypeEmployeur() {
        return typeEmployeur;
    }

    public void setTypeEmployeur(String typeEmployeur) {
        this.typeEmployeur = typeEmployeur;
    }

    public Date getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(Date dateEntree) {
        this.dateEntree = dateEntree;
    }

    public Date getDateRecrutement() {
        return dateRecrutement;
    }

    public void setDateRecrutement(Date dateRecrutement) {
        this.dateRecrutement = dateRecrutement;
    }

    public Date getDateFinEssai() {
        return dateFinEssai;
    }

    public void setDateFinEssai(Date dateFinEssai) {
        this.dateFinEssai = dateFinEssai;
    }

    public Date getDateTitularisation() {
        return dateTitularisation;
    }

    public void setDateTitularisation(Date dateTitularisation) {
        this.dateTitularisation = dateTitularisation;
    }

    public boolean isChefFamille() {
        return chefFamille;
    }

    public void setChefFamille(boolean chefFamille) {
        this.chefFamille = chefFamille;
    }

    public boolean isSalaireUnique() {
        return salaireUnique;
    }

    public void setSalaireUnique(boolean salaireUnique) {
        this.salaireUnique = salaireUnique;
    }

    public boolean isAllocationFamille() {
        return allocationFamille;
    }

    public void setAllocationFamille(boolean allocationFamille) {
        this.allocationFamille = allocationFamille;
    }

    public String getNumeroSecuriteSociale() {
        return numeroSecuriteSociale;
    }

    public void setNumeroSecuriteSociale(String numeroSecuriteSociale) {
        this.numeroSecuriteSociale = numeroSecuriteSociale;
    }

    public Date getDateAffiliation() {
        return dateAffiliation;
    }

    public void setDateAffiliation(Date dateAffiliation) {
        this.dateAffiliation = dateAffiliation;
    }

    public boolean isExonereeSecuriteSociale() {
        return exonereeSecuriteSociale;
    }

    public void setExonereeSecuriteSociale(boolean exonereeSecuriteSociale) {
        this.exonereeSecuriteSociale = exonereeSecuriteSociale;
    }

    public Date getDateDebutExonereeSecuriteSociale() {
        return dateDebutExonereeSecuriteSociale;
    }

    public void setDateDebutExonereeSecuriteSociale(Date dateDebutExonereeSecuriteSociale) {
        this.dateDebutExonereeSecuriteSociale = dateDebutExonereeSecuriteSociale;
    }

    public Date getDateFinExonereeSecuriteSociale() {
        return dateFinExonereeSecuriteSociale;
    }

    public void setDateFinExonereeSecuriteSociale(Date dateFinExonereeSecuriteSociale) {
        this.dateFinExonereeSecuriteSociale = dateFinExonereeSecuriteSociale;
    }

    public boolean isAffiliationAssuranceGroupe() {
        return affiliationAssuranceGroupe;
    }

    public void setAffiliationAssuranceGroupe(boolean affiliationAssuranceGroupe) {
        this.affiliationAssuranceGroupe = affiliationAssuranceGroupe;
    }

    public String getNomAssurance() {
        return nomAssurance;
    }

    public void setNomAssurance(String nomAssurance) {
        this.nomAssurance = nomAssurance;
    }

    public String getNumeroAffiliationAssurance() {
        return numeroAffiliationAssurance;
    }

    public void setNumeroAffiliationAssurance(String numeroAffiliationAssurance) {
        this.numeroAffiliationAssurance = numeroAffiliationAssurance;
    }

    public Date getDateAffiliationAssurance() {
        return dateAffiliationAssurance;
    }

    public void setDateAffiliationAssurance(Date dateAffiliationAssurance) {
        this.dateAffiliationAssurance = dateAffiliationAssurance;
    }

    public boolean isAffiliationMutuelle() {
        return affiliationMutuelle;
    }

    public void setAffiliationMutuelle(boolean affiliationMutuelle) {
        this.affiliationMutuelle = affiliationMutuelle;
    }

    public String getNomMutuelle() {
        return nomMutuelle;
    }

    public void setNomMutuelle(String nomMutuelle) {
        this.nomMutuelle = nomMutuelle;
    }

    public String getNumeroAffiliationMutuelle() {
        return numeroAffiliationMutuelle;
    }

    public void setNumeroAffiliationMutuelle(String numeroAffiliationMutuelle) {
        this.numeroAffiliationMutuelle = numeroAffiliationMutuelle;
    }

    public Date getDateAffiliationMutuelle() {
        return dateAffiliationMutuelle;
    }

    public void setDateAffiliationMutuelle(Date dateAffiliationMutuelle) {
        this.dateAffiliationMutuelle = dateAffiliationMutuelle;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getEchelon() {
        return echelon;
    }

    public void setEchelon(String echelon) {
        this.echelon = echelon;
    }

    public Date getDateSituation() {
        return dateSituation;
    }

    public void setDateSituation(Date dateSituation) {
        this.dateSituation = dateSituation;
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

    public String getStructureAttache() {
        return structureAttache;
    }

    public void setStructureAttache(String structureAttache) {
        this.structureAttache = structureAttache;
    }

    public String getDateAffectation() {
        return dateAffectation;
    }

    public void setDateAffectation(String dateAffectation) {
        this.dateAffectation = dateAffectation;
    }

    public String getMotifDepart() {
        return motifDepart;
    }

    public void setMotifDepart(String motifDepart) {
        this.motifDepart = motifDepart;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }

    public int getSituation() {
        return situation;
    }

    public void setSituation(int situation) {
        this.situation = situation;
    }

    public String getGrilleSalaire() {
        return grilleSalaire;
    }

    public void setGrilleSalaire(String grilleSalaire) {
        this.grilleSalaire = grilleSalaire;
    }

    public double getSalaireDeBase() {
        return salaireDeBase;
    }

    public void setSalaireDeBase(double salaireDeBase) {
        this.salaireDeBase = salaireDeBase;
    }

    public String getModeDePaiement() {
        return modeDePaiement;
    }

    public void setModeDePaiement(String modeDePaiement) {
        this.modeDePaiement = modeDePaiement;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public String getNomBanque() {
        return nomBanque;
    }

    public void setNomBanque(String nomBanque) {
        this.nomBanque = nomBanque;
    }

    public String getNomAgence() {
        return nomAgence;
    }

    public void setNomAgence(String nomAgence) {
        this.nomAgence = nomAgence;
    }

    public double getMontantAssurance() {
        return montantAssurance;
    }

    public void setMontantAssurance(double montantAssurance) {
        this.montantAssurance = montantAssurance;
    }

    public double getMontantMutuelle() {
        return montantMutuelle;
    }

    public void setMontantMutuelle(double montantMutuelle) {
        this.montantMutuelle = montantMutuelle;
    }
}
