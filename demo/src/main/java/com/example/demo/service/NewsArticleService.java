package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.NewsArticle;
import com.example.demo.repository.NewsArticleRepository;

@Service
public class NewsArticleService {

    @Autowired
    private NewsArticleRepository repo;

    public NewsArticle saveNewsArticle(NewsArticle article) {
        return repo.save(article);
    }

    public List<NewsArticle> getAll() {
        return repo.findAll();
    }

    public NewsArticle getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
