package com.example.cvai.Entity;

public class FilterResult {
    private int keywordCount;
    private int experienceDuration;
    private String message;
    private double score; // Add this field

    // Getters and setters
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
