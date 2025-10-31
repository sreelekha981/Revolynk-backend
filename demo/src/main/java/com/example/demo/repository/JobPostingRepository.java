package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Job;

public interface JobPostingRepository extends JpaRepository<Job, Long> {
}
