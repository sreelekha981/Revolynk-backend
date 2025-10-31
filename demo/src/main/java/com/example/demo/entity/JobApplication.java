package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_applications")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobId;

    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
    private String gender;

    private int experienceYears;
    private int experienceMonths;
    private String currentSalary;
    private String expectedSalary;
    private String currentLocation;
    private String noticePeriod;
    private String skills;
    private String willingToRelocate;
    private String resumeFile;

    private String status = "Pending";
    private LocalDateTime appliedOn = LocalDateTime.now();

    // ===== Getters & Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public int getExperienceYears() { return experienceYears; }
    public void setExperienceYears(int experienceYears) { this.experienceYears = experienceYears; }

    public int getExperienceMonths() { return experienceMonths; }
    public void setExperienceMonths(int experienceMonths) { this.experienceMonths = experienceMonths; }

    public String getCurrentSalary() { return currentSalary; }
    public void setCurrentSalary(String currentSalary) { this.currentSalary = currentSalary; }

    public String getExpectedSalary() { return expectedSalary; }
    public void setExpectedSalary(String expectedSalary) { this.expectedSalary = expectedSalary; }

    public String getCurrentLocation() { return currentLocation; }
    public void setCurrentLocation(String currentLocation) { this.currentLocation = currentLocation; }

    public String getNoticePeriod() { return noticePeriod; }
    public void setNoticePeriod(String noticePeriod) { this.noticePeriod = noticePeriod; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public String getWillingToRelocate() { return willingToRelocate; }
    public void setWillingToRelocate(String willingToRelocate) { this.willingToRelocate = willingToRelocate; }

    public String getResumeFile() { return resumeFile; }
    public void setResumeFile(String resumeFile) { this.resumeFile = resumeFile; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getAppliedOn() { return appliedOn; }
    public void setAppliedOn(LocalDateTime appliedOn) { this.appliedOn = appliedOn; }
}
