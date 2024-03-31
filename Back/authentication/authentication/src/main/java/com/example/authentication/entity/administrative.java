package com.example.authentication.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class administrative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
//    private String typeContrat;
//    private String typeEmployeur;
//    @JsonFormat(pattern="dd/MM/yyyy")
//    private Date dateEntree;
//    @JsonFormat(pattern="dd/MM/yyyy")
//    private Date dateRecrutement;
//    private Date dateFinEssai;
//    private Date dateTitularisation;
//
//
//    public Integer getEmployeeId() {
//        return employeeId;
//    }
//
//    public void setEmployeeId(Integer employeeId) {
//        this.employeeId = employeeId;
//    }
//
//    public String getTypeContrat() {
//        return typeContrat;
//    }
//
//    public void setTypeContrat(String typeContrat) {
//        this.typeContrat = typeContrat;
//    }
//
//    public String getTypeEmployeur() {
//        return typeEmployeur;
//    }
//
//    public void setTypeEmployeur(String typeEmployeur) {
//        this.typeEmployeur = typeEmployeur;
//    }
//
//    public Date getDateEntree() {
//        return dateEntree;
//    }
//
//    public void setDateEntree(Date dateEntree) {
//        this.dateEntree = dateEntree;
//    }
//
//    public Date getDateRecrutement() {
//        return dateRecrutement;
//    }
//
//    public void setDateRecrutement(Date dateRecrutement) {
//        this.dateRecrutement = dateRecrutement;
//    }
//
//    public Date getDateFinEssai() {
//        return dateFinEssai;
//    }
//
//    public void setDateFinEssai(Date dateFinEssai) {
//        this.dateFinEssai = dateFinEssai;
//    }
//
//    public Date getDateTitularisation() {
//        return dateTitularisation;
//    }
//
//    public void setDateTitularisation(Date dateTitularisation) {
//        this.dateTitularisation = dateTitularisation;
//    }
}
