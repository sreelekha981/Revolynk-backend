package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "perspective_accordion")
public class PerspectiveAccordion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accordionTitle;
    private String accordionDescription;

    @ManyToOne
    @JoinColumn(name = "perspective_id")
    private Perspective perspective;

    // ===== Getters and Setters =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccordionTitle() {
        return accordionTitle;
    }

    public void setAccordionTitle(String accordionTitle) {
        this.accordionTitle = accordionTitle;
    }

    public String getAccordionDescription() {
        return accordionDescription;
    }

    public void setAccordionDescription(String accordionDescription) {
        this.accordionDescription = accordionDescription;
    }

    public Perspective getPerspective() {
        return perspective;
    }

    public void setPerspective(Perspective perspective) {
        this.perspective = perspective;
    }
}
