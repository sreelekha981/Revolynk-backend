package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.LiveInterview;
import com.example.demo.repository.LiveInterviewRepository;

@Service
public class LiveInterviewService {
    private final LiveInterviewRepository repo;

    public LiveInterviewService(LiveInterviewRepository repo) {
        this.repo = repo;
    }

    public LiveInterview save(LiveInterview interview) {
        return repo.save(interview);
    }

    public List<LiveInterview> getAll() {
        return repo.findAll();
    }

    public Optional<LiveInterview> getById(Long id) {
        return repo.findById(id);
    }

    public boolean deleteById(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
