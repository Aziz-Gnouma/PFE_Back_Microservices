package com.example.cvai.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @Lob
    private byte[] fileData;
    @ManyToMany
    @JoinTable(
            name = "cv_offre_emploi",
            joinColumns = @JoinColumn(name = "cv_id"),
            inverseJoinColumns = @JoinColumn(name = "offre_emploi_id")
    )
    private List<OffreEmploi> offreEmplois;

    public List<OffreEmploi> getOffreEmplois() {
        return offreEmplois;
    }

    public void setOffreEmplois(List<OffreEmploi> offreEmplois) {
        this.offreEmplois = offreEmplois;
    }
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
