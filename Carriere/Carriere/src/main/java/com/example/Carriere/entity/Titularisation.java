package com.example.Carriere.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Titularisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_Titularisation;
    private String matricule;



    public Date getOk() {
        return ok;
    }

    public void setOk(Date ok) {
        this.ok = ok;
    }
    @Column(name = "Date_Tituluration")
    private Date ok;





    private Date DateAjouter;

    public Date getDateAjouter() {
        return DateAjouter;
    }

    public void setDateAjouter(Date dateAjouter) {
        DateAjouter = dateAjouter;
    }

    private String situation;

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Integer getId_Titularisation() {
        return Id_Titularisation;
    }

    public void setId_Titularisation(Integer id_Titularisation) {
        Id_Titularisation = id_Titularisation;
    }


    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }
}
