package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
