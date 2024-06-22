    package com.example.congesabs.entity;

    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;

    @Entity
    public class TypeCongee {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nom;
        private int nombreJours;
        private String code;
        private boolean isCumulative; // Indicates whether the congé is cumulative or not

        // Constructors
        public TypeCongee() {
        }

        // Getters and Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public int getNombreJours() {
            return nombreJours;
        }

        public void setNombreJours(int nombreJours) {
            this.nombreJours = nombreJours;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public boolean isCumulative() {
            return isCumulative;
        }

        public void setCumulative(boolean cumulative) {
            this.isCumulative = cumulative;
        }
    }