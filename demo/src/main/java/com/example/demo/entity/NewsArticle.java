package com.example.demo.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "news_article")
public class NewsArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String articleTitle;
    private String subtitle;
    private String topic;
    private String industry;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(columnDefinition = "LONGTEXT")
    private String mainContent;

    private String heading;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    private String imagePath;

    // ✅ Use JSON columns for lists instead of ElementCollection
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> bulletPoints;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> sectionTitle;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> sectionContent;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> accordionTitle;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> accordionContent;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> authorName;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> designation;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> linkedinUrl;

    // ---------- Getters and Setters ----------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMainContent() {
        return mainContent;
    }

    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<String> getBulletPoints() {
        return bulletPoints;
    }

    public void setBulletPoints(List<String> bulletPoints) {
        this.bulletPoints = bulletPoints;
    }

    public List<String> getSectionTitle() {
        return sectionTitle;
    }

    public void setSectionTitle(List<String> sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

    public List<String> getSectionContent() {
        return sectionContent;
    }

    public void setSectionContent(List<String> sectionContent) {
        this.sectionContent = sectionContent;
    }

    public List<String> getAccordionTitle() {
        return accordionTitle;
    }

    public void setAccordionTitle(List<String> accordionTitle) {
        this.accordionTitle = accordionTitle;
    }

    public List<String> getAccordionContent() {
        return accordionContent;
    }

    public void setAccordionContent(List<String> accordionContent) {
        this.accordionContent = accordionContent;
    }

    public List<String> getAuthorName() {
        return authorName;
    }

    public void setAuthorName(List<String> authorName) {
        this.authorName = authorName;
    }

    public List<String> getDesignation() {
        return designation;
    }

    public void setDesignation(List<String> designation) {
        this.designation = designation;
    }

    public List<String> getLinkedinUrl() {
        return linkedinUrl;
    }

    public void setLinkedinUrl(List<String> linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
    }
}
