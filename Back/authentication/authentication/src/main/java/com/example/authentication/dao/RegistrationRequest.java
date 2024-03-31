package com.example.authentication.dao;

import com.example.authentication.entity.Entreprise;
import com.example.authentication.entity.User;
import com.example.authentication.entity.administrative;

public class RegistrationRequest {

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    private User user;
    private String roleName;

    private Entreprise entreprise;

    private administrative  Administrative;
    private String entrepriseName;

    public String getEntrepriseName() {
        return entrepriseName;
    }

    public void setEntrepriseName(String entrepriseName) {
        this.entrepriseName = entrepriseName;
    }

    public administrative getAdministrative() {
        return Administrative;
    }

    public void setAdministrative(administrative administrative) {
        Administrative = administrative;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }
}
