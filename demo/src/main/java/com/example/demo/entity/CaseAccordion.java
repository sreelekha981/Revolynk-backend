package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class CaseAccordion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String description;

    // 👇 This is the missing relationship and setter
    @ManyToOne
    @JoinColumn(name = "case_study_id")
    private CaseStudy caseStudy;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    // 🔥 Add these two methods
    public CaseStudy getCaseStudy() { return caseStudy; }
    public void setCaseStudy(CaseStudy caseStudy) { this.caseStudy = caseStudy; }
}
