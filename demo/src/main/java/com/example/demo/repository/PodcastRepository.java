package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Podcast;

@Repository
public interface PodcastRepository extends JpaRepository<Podcast, Long> {
}
