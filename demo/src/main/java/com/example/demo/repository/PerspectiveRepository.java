package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Perspective;

public interface PerspectiveRepository extends JpaRepository<Perspective, Long> {}
 