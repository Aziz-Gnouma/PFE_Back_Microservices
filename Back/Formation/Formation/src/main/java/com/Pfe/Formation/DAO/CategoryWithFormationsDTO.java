package com.Pfe.Formation.DAO;

import com.Pfe.Formation.entity.Formation;

import java.util.List;

public class CategoryWithFormationsDTO {
    private Long id;
    private String name;
    private String entreprise;
    private List<Formation> formations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public List<Formation> getFormations() {
        return formations;
    }

    public void setFormations(List<Formation> formations) {
        this.formations = formations;
    }
}
