package com.example.demo.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

/**
 * Blog entity representing blog posts with sections, accordions, and authors.
 * Uses ElementCollection for embedding small, dependent entities.
 */
@Entity
@Table(name = "blogs")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String topic;
    private String industry;
    private LocalDate date;

    @Column(length = 2000)
    private String introParagraph;

    @ElementCollection
    @CollectionTable(name = "blog_sections", joinColumns = @JoinColumn(name = "blog_id"))
    private List<Section> sections;

    @Column(name = "image_path")
    private String imagePath;

    @Column(length = 2000)
    private String paragraphAfterImage;

    @ElementCollection
    @CollectionTable(name = "blog_accordions", joinColumns = @JoinColumn(name = "blog_id"))
    private List<Accordion> accordions;

    @ElementCollection
    @CollectionTable(name = "blog_authors", joinColumns = @JoinColumn(name = "blog_id"))
    private List<Author> authors;

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getIntroParagraph() { return introParagraph; }
    public void setIntroParagraph(String introParagraph) { this.introParagraph = introParagraph; }

    public List<Section> getSections() { return sections; }
    public void setSections(List<Section> sections) { this.sections = sections; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public String getParagraphAfterImage() { return paragraphAfterImage; }
    public void setParagraphAfterImage(String paragraphAfterImage) { this.paragraphAfterImage = paragraphAfterImage; }

    public List<Accordion> getAccordions() { return accordions; }
    public void setAccordions(List<Accordion> accordions) { this.accordions = accordions; }

    public List<Author> getAuthors() { return authors; }
    public void setAuthors(List<Author> authors) { this.authors = authors; }

    // --- Embedded Classes ---

    @Embeddable
    public static class Section {
        private String sectionTitle;

        @Column(length = 3000)
        private String sectionContent;

        public String getSectionTitle() { return sectionTitle; }
        public void setSectionTitle(String sectionTitle) { this.sectionTitle = sectionTitle; }

        public String getSectionContent() { return sectionContent; }
        public void setSectionContent(String sectionContent) { this.sectionContent = sectionContent; }
    }

    @Embeddable
    public static class Accordion {
        private String accordionTitle;

        @Column(length = 3000)
        private String accordionDescription;

        public String getAccordionTitle() { return accordionTitle; }
        public void setAccordionTitle(String accordionTitle) { this.accordionTitle = accordionTitle; }

        public String getAccordionDescription() { return accordionDescription; }
        public void setAccordionDescription(String accordionDescription) { this.accordionDescription = accordionDescription; }
    }

    @Embeddable
    public static class Author {
        private String authorName;
        private String designation;
        private String linkedInUrl;

        public String getAuthorName() { return authorName; }
        public void setAuthorName(String authorName) { this.authorName = authorName; }

        public String getDesignation() { return designation; }
        public void setDesignation(String designation) { this.designation = designation; }

        public String getLinkedInUrl() { return linkedInUrl; }
        public void setLinkedInUrl(String linkedInUrl) { this.linkedInUrl = linkedInUrl; }
    }
}
