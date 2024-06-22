package com.example.sanctionsrecompences.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Entity
public class Sanction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer matricule;
private String RHMatricule;
private String entreprise;
    private String type;
    @JsonFormat(pattern = "MM-dd-yyyy")
    private Date DateDemission;
    private String Autorite;
    private String Description;
    private String Gravite;
    @JsonFormat(pattern = "MM-dd-yyyy")
    private Date Duree;
    private String StatutA;

    // Getters and setters...


    public Date getDateDemission() {
        return DateDemission;
    }

    public void setDateDemission(Date dateDemission) {
        DateDemission = dateDemission;
    }

    public String getAutorite() {
        return Autorite;
    }

    public void setAutorite(String autorite) {
        Autorite = autorite;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getGravite() {
        return Gravite;
    }

    public void setGravite(String gravite) {
        Gravite = gravite;
    }

    public Date getDuree() {
        return Duree;
    }

    public void setDuree(Date duree) {
        Duree = duree;
    }





    public Integer getMatricule() {
        return matricule;
    }

    public void setMatricule(Integer matricule) {
        this.matricule = matricule;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRHMatricule() {
        return RHMatricule;
    }

    public void setRHMatricule(String RHMatricule) {
        this.RHMatricule = RHMatricule;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatutA() {
        return StatutA;
    }

    public void setStatutA(String statutA) {
        StatutA = statutA;
    }

    // Projection
    @Projection(name = "fullSanction", types = Sanction.class)
    interface SanctionProjection {
        Integer getEmployeeID();
        String getSanctionType();
        Date getDate_démission();
    }
}
