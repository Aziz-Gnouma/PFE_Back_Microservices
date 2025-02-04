package com.example.authentication.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "UserInfo")
public class User {


    private String email;

    private String Private_email;

    private String userFirstName;
    private String userLastName;

    @Column(name = "CIN")
    private int cin;
    private String userPassword;

    private String civility;
    @Id
    private String matricule;
    @JsonFormat(pattern="dd/MM/yyyy")

    private Date dateOfBirth;
    private String placeOfBirth;
    private String nationality;
    private String gender;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date cinDate;
    private Integer phoneNumber;
    private String address;
    private String pays;
    private  String CodePostal;
    public String getCodePostal() {
        return CodePostal;
    }
    private String niveauEtude;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dateDernierDiplome;

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

    public void setCodePostal(String codePostal) {
        CodePostal = codePostal;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "USER__ENTREPRISE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ENTREPRISE_ID")}
    )
    private Set<Entreprise> entreprise;



    public String getCivility() {
        return civility;
    }

    public void setCivility(String civility) {
        this.civility = civility;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    @JsonFormat(pattern="dd-MM-yyyy")
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getCinDate() {
        return cinDate;
    }

    public void setCinDate(Date cinDate) {
        this.cinDate = cinDate;
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

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getPrivate_email() {
        return Private_email;
    }

    public void setPrivate_email(String private_email) {
        Private_email = private_email;
    }

    // Getter and setter for userFirstName field
    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    // Getter and setter for userLastName field
    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    // Getter and setter for email field
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and setter for userPassword field
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    // Getter and setter for cin field
    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    // Getter and setter for role field
    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    // Getter and setter for entreprise field
    public Set<Entreprise> getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Set<Entreprise> entreprise) {
        this.entreprise = entreprise;
    }
}
