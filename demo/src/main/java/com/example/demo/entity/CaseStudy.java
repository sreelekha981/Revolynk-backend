package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "case_study")
public class CaseStudy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String industry;
    private String category;

    @Column(columnDefinition = "TEXT")
    private String overview;

    @Column(columnDefinition = "TEXT")
    private String challenge;

    @Column(columnDefinition = "TEXT")
    private String outcome;

    @Column(columnDefinition = "TEXT")
    private String madeWork;

    @Column(columnDefinition = "TEXT")
    private String challengePoints;

    @Column(columnDefinition = "TEXT")
    private String constraints;

    @Column(columnDefinition = "TEXT")
    private String approachSteps;

    private String heroImage;
    private String challengeImage;
    private String approachImage;
    private String madeWorkImage;

    private String createdAt;

    // ----- Getters and Setters -----

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getMadeWork() {
        return madeWork;
    }

    public void setMadeWork(String madeWork) {
        this.madeWork = madeWork;
    }

    public String getChallengePoints() {
        return challengePoints;
    }

    public void setChallengePoints(String challengePoints) {
        this.challengePoints = challengePoints;
    }

    public String getConstraints() {
        return constraints;
    }

    public void setConstraints(String constraints) {
        this.constraints = constraints;
    }

    public String getApproachSteps() {
        return approachSteps;
    }

    public void setApproachSteps(String approachSteps) {
        this.approachSteps = approachSteps;
    }

    public String getHeroImage() {
        return heroImage;
    }

    public void setHeroImage(String heroImage) {
        this.heroImage = heroImage;
    }

    public String getChallengeImage() {
        return challengeImage;
    }

    public void setChallengeImage(String challengeImage) {
        this.challengeImage = challengeImage;
    }

    public String getApproachImage() {
        return approachImage;
    }

    public void setApproachImage(String approachImage) {
        this.approachImage = approachImage;
    }

    public String getMadeWorkImage() {
        return madeWorkImage;
    }

    public void setMadeWorkImage(String madeWorkImage) {
        this.madeWorkImage = madeWorkImage;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
