package com.example.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class LiveInterview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String interviewTitle;
    private String topic;
    private String industry;
    private LocalDate date;
    private String imagePath;

    @Column(length = 1000)
    private String quote;
    private String intervieweeName;
    private String intervieweeDesignation;

    @OneToMany(mappedBy = "liveInterview", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<QASection> qaSections = new ArrayList<>();

    @OneToMany(mappedBy = "liveInterview", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<InterviewQuestion> questions = new ArrayList<>();

    // Getters and setters
    public Long getId() { return id; }
    public String getInterviewTitle() { return interviewTitle; }
    public void setInterviewTitle(String interviewTitle) { this.interviewTitle = interviewTitle; }
    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public String getQuote() { return quote; }
    public void setQuote(String quote) { this.quote = quote; }
    public String getIntervieweeName() { return intervieweeName; }
    public void setIntervieweeName(String intervieweeName) { this.intervieweeName = intervieweeName; }
    public String getIntervieweeDesignation() { return intervieweeDesignation; }
    public void setIntervieweeDesignation(String intervieweeDesignation) { this.intervieweeDesignation = intervieweeDesignation; }
    public List<QASection> getQaSections() { return qaSections; }
    public void setQaSections(List<QASection> qaSections) { this.qaSections = qaSections; }
    public List<InterviewQuestion> getQuestions() { return questions; }
    public void setQuestions(List<InterviewQuestion> questions) { this.questions = questions; }
}
