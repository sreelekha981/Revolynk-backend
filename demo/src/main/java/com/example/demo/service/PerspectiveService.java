package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Perspective;
import com.example.demo.repository.PerspectiveRepository;

@Service
public class PerspectiveService {

    @Autowired
    private PerspectiveRepository repo;

    public List<Perspective> getAll() {
        return repo.findAll();
    }

    public Perspective getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Perspective save(Perspective perspective) {
        return repo.save(perspective);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public Perspective update(Long id, Perspective perspective) {
        Perspective existing = getById(id);
        if (existing == null) return null;

        existing.setArticleTitle(perspective.getArticleTitle());
        existing.setSubtitle(perspective.getSubtitle());
        existing.setTopic(perspective.getTopic());
        existing.setIndustry(perspective.getIndustry());
        existing.setDate(perspective.getDate());
        existing.setImagePath(perspective.getImagePath());

        // ✅ Update brief lists
        existing.setBriefSectionContent(perspective.getBriefSectionContent());
        existing.setBriefKeyInsight(perspective.getBriefKeyInsight());

        // ✅ Update section lists
        existing.setSectionTitle(perspective.getSectionTitle());
        existing.setSectionContent(perspective.getSectionContent());

        // ✅ Update accordion lists
        existing.setAccordionTitle(perspective.getAccordionTitle());
        existing.setAccordionDescription(perspective.getAccordionDescription());

        return repo.save(existing);
    }
}
