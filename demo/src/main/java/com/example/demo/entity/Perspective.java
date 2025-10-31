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
@Table(name = "perspective")
public class Perspective {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String articleTitle;
    private String subtitle;
    private String topic;
    private String industry;

    @Temporal(TemporalType.DATE)
    private Date date;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> briefSectionContent;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> briefKeyInsight;

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
    private List<String> accordionDescription;

    private String imagePath;

    // ✅ Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getArticleTitle() { return articleTitle; }
    public void setArticleTitle(String articleTitle) { this.articleTitle = articleTitle; }

    public String getSubtitle() { return subtitle; }
    public void setSubtitle(String subtitle) { this.subtitle = subtitle; }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public List<String> getBriefSectionContent() { return briefSectionContent; }
    public void setBriefSectionContent(List<String> briefSectionContent) { this.briefSectionContent = briefSectionContent; }

    public List<String> getBriefKeyInsight() { return briefKeyInsight; }
    public void setBriefKeyInsight(List<String> briefKeyInsight) { this.briefKeyInsight = briefKeyInsight; }

    public List<String> getSectionTitle() { return sectionTitle; }
    public void setSectionTitle(List<String> sectionTitle) { this.sectionTitle = sectionTitle; }

    public List<String> getSectionContent() { return sectionContent; }
    public void setSectionContent(List<String> sectionContent) { this.sectionContent = sectionContent; }

    public List<String> getAccordionTitle() { return accordionTitle; }
    public void setAccordionTitle(List<String> accordionTitle) { this.accordionTitle = accordionTitle; }

    public List<String> getAccordionDescription() { return accordionDescription; }
    public void setAccordionDescription(List<String> accordionDescription) { this.accordionDescription = accordionDescription; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
}
