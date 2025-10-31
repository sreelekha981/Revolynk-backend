package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Podcast;
import com.example.demo.service.PodcastService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/podcasts")
@CrossOrigin(origins = "*")
public class PodcastController {

    private final PodcastService podcastService;
    private final ObjectMapper objectMapper;

    public PodcastController(PodcastService podcastService, ObjectMapper objectMapper) {
        this.podcastService = podcastService;
        this.objectMapper = objectMapper;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<String> createPodcast(
            @RequestPart("podcast") String podcastJson,
            @RequestPart(required = false) List<MultipartFile> audioFile0,
            @RequestPart(required = false) List<MultipartFile> audioFile1,
            @RequestPart(required = false) List<MultipartFile> audioFile2) {

        try {
            Podcast podcast = objectMapper.readValue(podcastJson, Podcast.class);

            List<MultipartFile> audioFiles = new java.util.ArrayList<>();
            if (audioFile0 != null) audioFiles.addAll(audioFile0);
            if (audioFile1 != null) audioFiles.addAll(audioFile1);
            if (audioFile2 != null) audioFiles.addAll(audioFile2);

            podcastService.savePodcast(podcast, audioFiles);
            return ResponseEntity.ok("Podcast saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error saving podcast: " + e.getMessage());
        }
    }

   @GetMapping
public List<Podcast> getAllPodcasts() {
    return podcastService.getAllPodcasts();
}

    

    @GetMapping("/{id}")
    public Podcast getOne(@PathVariable Long id) {
        return podcastService.getPodcastById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        podcastService.deletePodcast(id);
        return ResponseEntity.ok("Podcast deleted successfully.");
    }
}
