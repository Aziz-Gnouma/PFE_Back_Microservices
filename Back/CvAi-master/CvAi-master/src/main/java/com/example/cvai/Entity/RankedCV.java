package com.example.cvai.Entity;

public class RankedCV {
    private Long id;
    private String name;
    private String email;
    private String cvText;
    private int keywordCount;
    private int experienceDuration;
    private double score;

    public RankedCV(Long id, String name, String email, String cvText, int keywordCount, int experienceDuration, double score) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cvText = cvText;
        this.keywordCount = keywordCount;
        this.experienceDuration = experienceDuration;
        this.score = score;
    }
    // Getters and setters

    public String getCvText() {
        return cvText;
    }

    public void setCvText(String cvText) {
        this.cvText = cvText;
    }

    public int getKeywordCount() {
        return keywordCount;
    }

    public void setKeywordCount(int keywordCount) {
        this.keywordCount = keywordCount;
    }

    public int getExperienceDuration() {
        return experienceDuration;
    }

    public void setExperienceDuration(int experienceDuration) {
        this.experienceDuration = experienceDuration;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}