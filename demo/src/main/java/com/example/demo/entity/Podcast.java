package com.example.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "podcasts")
public class Podcast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String subtitle;
    private String topic;
    private String industry;

    @JsonFormat(pattern = "yyyy-MM-dd")  // ✅ Fix date parsing from JSON
    private LocalDate date;

    @OneToMany(mappedBy = "podcast", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PodcastEpisode> episodes = new ArrayList<>();

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSubtitle() { return subtitle; }
    public void setSubtitle(String subtitle) { this.subtitle = subtitle; }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public List<PodcastEpisode> getEpisodes() { return episodes; }
    public void setEpisodes(List<PodcastEpisode> episodes) {
        this.episodes = episodes;
        this.episodes.forEach(e -> e.setPodcast(this));
    }
}
