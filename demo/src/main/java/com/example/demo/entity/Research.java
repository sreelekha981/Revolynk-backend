package com.example.demo.entity;
import java.time.LocalDate;
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
public class Research {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String topic;
    private String industry;
    private LocalDate date;


    @Column(length = 5000)
    private String abstractText;

    private String documentPath;

    // --- Relationships ---
    @JsonManagedReference
    @OneToMany(mappedBy = "research", cascade = CascadeType.ALL)
    private List<KeyFinding> keyFindings;

    @JsonManagedReference
    @OneToMany(mappedBy = "research", cascade = CascadeType.ALL)
    private List<AccordionSection> accordionSections;

    @JsonManagedReference
    @OneToMany(mappedBy = "research", cascade = CascadeType.ALL)
    private List<ResAuthor> authors;

    @JsonManagedReference
    @OneToMany(mappedBy = "research", cascade = CascadeType.ALL)
    private List<ContentBlock> contentBlocks;

    // --- Getters & Setters ---
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

  
public LocalDate getDate() {
    return date;
}

public void setDate(LocalDate date) {
    this.date = date;
}

    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    public List<KeyFinding> getKeyFindings() {
        return keyFindings;
    }

    public void setKeyFindings(List<KeyFinding> keyFindings) {
        this.keyFindings = keyFindings;
    }

    public List<AccordionSection> getAccordionSections() {
        return accordionSections;
    }

    public void setAccordionSections(List<AccordionSection> accordionSections) {
        this.accordionSections = accordionSections;
    }

    public List<ResAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<ResAuthor> authors) {
        this.authors = authors;
    }

    public List<ContentBlock> getContentBlocks() {
        return contentBlocks;
    }

    public void setContentBlocks(List<ContentBlock> contentBlocks) {
        this.contentBlocks = contentBlocks;
    }
}
