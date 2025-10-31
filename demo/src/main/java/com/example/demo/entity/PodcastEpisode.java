package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "podcast_episodes")
public class PodcastEpisode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String episodeTitle;
    private String description;
    private String audioPath; // Optional – stores file path or name

    @ManyToOne
    @JoinColumn(name = "podcast_id")
    private Podcast podcast;

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEpisodeTitle() { return episodeTitle; }
    public void setEpisodeTitle(String episodeTitle) { this.episodeTitle = episodeTitle; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getAudioPath() { return audioPath; }
    public void setAudioPath(String audioPath) { this.audioPath = audioPath; }

    public Podcast getPodcast() { return podcast; }
    public void setPodcast(Podcast podcast) { this.podcast = podcast; }
}
