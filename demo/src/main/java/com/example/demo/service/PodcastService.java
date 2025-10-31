package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Podcast;
import com.example.demo.entity.PodcastEpisode;
import com.example.demo.repository.PodcastRepository;

@Service
public class PodcastService {

    @Autowired
    private PodcastRepository podcastRepository;

    public void savePodcast(Podcast podcast, List<MultipartFile> audioFiles) throws IOException {
        // ✅ Save audio files to "uploads/podcasts"
        for (int i = 0; i < podcast.getEpisodes().size(); i++) {
            PodcastEpisode episode = podcast.getEpisodes().get(i);
            if (audioFiles != null && i < audioFiles.size()) {
                MultipartFile file = audioFiles.get(i);
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path path = Paths.get("uploads/podcasts/" + fileName);
                Files.createDirectories(path.getParent());
                Files.write(path, file.getBytes());
                episode.setAudioPath(path.toString());
            }
            episode.setPodcast(podcast);
        }

        podcastRepository.save(podcast);
    }

    public List<Podcast> getAllPodcasts() {
        return podcastRepository.findAll();
    }

    public Podcast getPodcastById(Long id) {
        return podcastRepository.findById(id).orElse(null);
    }

    public void deletePodcast(Long id) {
        podcastRepository.deleteById(id);
    }
}
