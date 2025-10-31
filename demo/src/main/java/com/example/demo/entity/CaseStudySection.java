package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class CaseStudySection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(length = 2000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "case_study_id")
    private CaseStudy caseStudy;

    // Getters & Setters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public CaseStudy getCaseStudy() { return caseStudy; }
    public void setCaseStudy(CaseStudy caseStudy) { this.caseStudy = caseStudy; }
}
