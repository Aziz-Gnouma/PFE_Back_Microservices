package com.example.authentication.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
@Component
public class UserDTO {


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
    @JsonFormat(pattern="dd-MM-yyyy")

    private Date dateOfBirth;
    private String placeOfBirth;
    private String nationality;
    private String gender;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date cinDate;
    private Integer phoneNumber;
    private String address;
    private String pays;
    private  String CodePostal;
    private String niveauEtude;

    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateDernierDiplome;

    public String getNiveauEtude() {
        return niveauEtude;
    }
    public String getCodePostal() {
        return CodePostal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrivate_email() {
        return Private_email;
    }

    public void setPrivate_email(String private_email) {
        Private_email = private_email;
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

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

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

    public void setCodePostal(String codePostal) {
        CodePostal = codePostal;
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
}

