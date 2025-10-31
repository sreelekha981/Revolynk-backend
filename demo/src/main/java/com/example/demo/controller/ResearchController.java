package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Research;
import com.example.demo.service.ResearchService;

@RestController
@RequestMapping("/api/research")
@CrossOrigin(origins = "*")
public class ResearchController {

    @Autowired
    private ResearchService researchService;

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<?> createResearch(
            @RequestParam("title") String title,
            @RequestParam("topic") String topic,
            @RequestParam("industry") String industry,
            @RequestParam("date") String date,
            @RequestParam("abstractText") String abstractText,
            @RequestParam(value = "authorNames", required = false) List<String> authorNames,
            @RequestParam(value = "authorDesignations", required = false) List<String> authorDesignations,
            @RequestParam(value = "authorLinkedIns", required = false) List<String> authorLinkedIns,
            @RequestParam(value = "keyFindings", required = false) List<String> keyFindings,
            @RequestParam(value = "sectionTitles", required = false) List<String> sectionTitles,
            @RequestParam(value = "sectionContents", required = false) List<String> sectionContents,
            @RequestParam(value = "accordionTitles", required = false) List<String> accordionTitles,
            @RequestParam(value = "accordionContents", required = false) List<String> accordionContents,
            @RequestParam(value = "document", required = false) MultipartFile document
    ) {
        try {
            Research saved = researchService.saveResearch(
                    title, topic, industry, date, authorNames, authorDesignations, authorLinkedIns,
                    keyFindings, sectionTitles, sectionContents, accordionTitles
            );
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("❌ Error saving research: " + e.getMessage());
        }
    }

    // ✅ READ (ALL)
    @GetMapping
    public List<Research> getAllResearch() {
        return researchService.getAllResearch();
    }

    // ✅ READ (ONE)
    @GetMapping("/{id}")
    public ResponseEntity<?> getResearchById(@PathVariable Long id) {
        Research research = researchService.getResearchById(id);
        if (research == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(research);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updateResearch(
            @PathVariable Long id,
            @RequestParam("title") String title,
            @RequestParam("topic") String topic,
            @RequestParam("industry") String industry,
            @RequestParam("date") String date,
            @RequestParam("abstractText") String abstractText,
            @RequestParam(value = "authorNames", required = false) List<String> authorNames,
            @RequestParam(value = "authorDesignations", required = false) List<String> authorDesignations,
            @RequestParam(value = "authorLinkedIns", required = false) List<String> authorLinkedIns,
            @RequestParam(value = "keyFindings", required = false) List<String> keyFindings,
            @RequestParam(value = "sectionTitles", required = false) List<String> sectionTitles,
            @RequestParam(value = "sectionContents", required = false) List<String> sectionContents,
            @RequestParam(value = "accordionTitles", required = false) List<String> accordionTitles,
            @RequestParam(value = "accordionContents", required = false) List<String> accordionContents,
            @RequestParam(value = "document", required = false) MultipartFile document
    ) {
        try {
            Research updated = researchService.updateResearch(
                    id, title, topic, industry, date, authorNames, authorDesignations, authorLinkedIns,
                    keyFindings, sectionTitles, sectionContents, accordionTitles
            );
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("❌ Error updating research: " + e.getMessage());
        }
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResearch(@PathVariable Long id) {
        try {
            researchService.deleteResearch(id);
            return ResponseEntity.ok("✅ Research deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("❌ Error deleting research: " + e.getMessage());
        }
    }
}
