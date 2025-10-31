package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LiveInterview;

public interface LiveInterviewRepository extends JpaRepository<LiveInterview, Long> {
}
