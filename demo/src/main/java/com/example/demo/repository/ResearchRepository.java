package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Research;

public interface ResearchRepository extends JpaRepository<Research, Long> {
}
