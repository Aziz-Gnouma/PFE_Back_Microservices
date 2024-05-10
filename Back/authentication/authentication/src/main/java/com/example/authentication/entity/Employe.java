package com.example.authentication.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Employe {



    @Id
    private String matricule;


    //@OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    //private Set<Child> enfants = new HashSet<>();

    private Integer phoneNumber;
    private String address;

    private String userFirstName;
    private String userLastName;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dateOfBirth;
    private String placeOfBirth;
    private String gender;
    private String civility;
    @Column(name = "CIN")
    private int cin;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date cinDate;
    private String nationality;
    private String codePostal;
    private String pays;
    private String email;
    private String niveauEtude;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dateDernierDiplome;

    //**** Informations administratives  **************************************//
    private String typeContrat;
    private String typeEmployeur;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateEntree;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateRecrutement;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateFinEssai;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateTitularisation;




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

}
