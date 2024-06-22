package org.vaadin.marcus.data;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Reclamation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reclamationId ;
    private String matricule;
    private String entreprise;

    private String firstName;
    private String lastName;
    private String tel;
    private String email;
    private LocalDate date;
    private String description;

    private String ReclamationStatut;

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public Long getReclamationId() {
        return reclamationId;
    }

    public void setReclamationId(Long reclamationId) {
        this.reclamationId = reclamationId;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReclamationStatut() {
        return ReclamationStatut;
    }

    public void setReclamationStatut(String reclamationStatut) {
        ReclamationStatut = reclamationStatut;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
