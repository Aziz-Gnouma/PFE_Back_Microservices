package com.example.Depart.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Depart {


    @Id
    @Column(name = "cin")
    private Integer Cin;

    @Column(name = "departDate")
    private Date DepartDate;

    @Column(name = "departType")
    private String DepartType;

    @Column(name = "etat")
    private Integer Etat;

    @Column(name = "exitReason")
    private String ExitReason;

    @Column(name = "rhRemarks")
    private String RhRemarks;



    public Integer getCin() {
        return Cin;
    }

    public void setCin(Integer cin) {
        Cin = cin;
    }

    public Integer getEtat() {
        return Etat;
    }

    public void setEtat(Integer etat) {
        Etat = etat;
    }

    public String getExitReason() {
        return ExitReason;
    }

    public void setExitReason(String exitReason) {
        ExitReason = exitReason;
    }

    public String getRhRemarks() {
        return RhRemarks;
    }

    public void setRhRemarks(String rhRemarks) {
        RhRemarks = rhRemarks;
    }

    public String getDepartType() {
        return DepartType;
    }

    public void setDepartType(String departType) {
        DepartType = departType;
    }

    public Date getDepartDate() {
        return DepartDate;
    }

    public void setDepartDate(Date departDate) {
        DepartDate = departDate;
    }



}
