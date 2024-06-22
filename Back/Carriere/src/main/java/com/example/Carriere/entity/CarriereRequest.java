package com.example.Carriere.entity;

public class CarriereRequest {
    private Avancement avancement;
    private Titularisation titularisation;
    private Structure structure;

    public Avancement getAvancement() {
        return avancement;
    }

    public void setAvancement(Avancement avancement) {
        this.avancement = avancement;
    }

    public Titularisation getTitularisation() {
        return titularisation;
    }

    public void setTitularisation(Titularisation titularisation) {
        this.titularisation = titularisation;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }
}
