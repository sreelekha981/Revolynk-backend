package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Job;
import com.example.demo.repository.JobPostingRepository;

@Service
public class JobPostingService {

    @Autowired
    private JobPostingRepository repository;

    public List<Job> getAllJobs() {
        return repository.findAll();
    }

    public Optional<Job> getJobById(Long id) {
        return repository.findById(id);
    }

    public Job saveJob(Job job) {
        return repository.save(job);
    }

    public void deleteJob(Long id) {
        repository.deleteById(id);
    }
}
