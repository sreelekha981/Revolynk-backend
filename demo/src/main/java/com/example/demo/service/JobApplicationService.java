package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.JobApplication;
import com.example.demo.repository.JobApplicationRepository;

@Service
public class JobApplicationService {

    private final JobApplicationRepository repo;

    public JobApplicationService(JobApplicationRepository repo) {
        this.repo = repo;
    }
    

    public JobApplication save(JobApplication app) {
        return repo.save(app);
    }

    public List<JobApplication> findAll() {
        return repo.findAll();
    }

    public JobApplication findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
