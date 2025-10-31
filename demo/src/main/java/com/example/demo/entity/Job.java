package com.example.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "job_postings")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String experience;
    private String location;
    private String type;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(length = 3000)
    private String description;

    @ElementCollection
    @CollectionTable(name = "job_profile_points", joinColumns = @JoinColumn(name = "job_id"))
    @Column(name = "profile_point")
    private List<String> profilePoints = new ArrayList<>();
    
    @ElementCollection
    @CollectionTable(name = "job_skills", joinColumns = @JoinColumn(name = "job_id"))
    @Column(name = "skill")
    private List<String> skills = new ArrayList<>();

    private String status = "Active";

    @PrePersist
    public void prePersist() {
        if (date == null) {
            date = LocalDate.now();
        }
    }

    // ===== Getters & Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getExperience() { return experience; }
    public void setExperience(String experience) { this.experience = experience; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getProfilePoints() { return profilePoints; }
    public void setProfilePoints(List<String> profilePoints) { this.profilePoints = profilePoints; }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
