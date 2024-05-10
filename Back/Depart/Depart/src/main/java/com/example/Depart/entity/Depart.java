package com.example.Depart.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Depart {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departId;

    @Column(name = "cin")
    private Integer Cin;

    @Column(name = "matricule")
    private String matricule;

    @Column(name = "departDate")
    private Date DepartDate;

    @Column(name = "departType")
    private String DepartType;

    @Column(name = "situation")
    private String situation;
    @Column(name = "employeeName")
    private String employeeName;
    @Column(name = "employeeEmail")
    private String employeeEmail;


    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public Integer getCin() {
        return Cin;
    }

    public void setCin(Integer cin) {
        Cin = cin;
    }



    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public int getDepartId() {
        return departId;
    }

    public void setDepartId(int departId) {
        this.departId = departId;
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
