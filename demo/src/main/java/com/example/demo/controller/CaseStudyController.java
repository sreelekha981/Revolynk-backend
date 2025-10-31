package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.CaseStudy;
import com.example.demo.service.CaseStudyService;

@RestController
@RequestMapping("/api/casestudies")
@CrossOrigin(origins = "*")
public class CaseStudyController {

    @Autowired
    private CaseStudyService caseStudyService;

    // ✅ CREATE a new Case Study (accepts text + images)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CaseStudy> createCaseStudy(
            @RequestParam String title,
            @RequestParam String industry,
            @RequestParam String category,
            @RequestParam(required = false) String overview,
            @RequestParam(required = false) String challenge,
            @RequestParam(required = false) String outcome,
            @RequestParam(required = false) String madeWork,
            @RequestParam(required = false) String challengePoints,
            @RequestParam(required = false) String constraints,
            @RequestParam(required = false) String approachSteps,
            @RequestParam(required = false) MultipartFile heroImage,
            @RequestParam(required = false) MultipartFile challengeImage,
            @RequestParam(required = false) MultipartFile approachImage,
            @RequestParam(required = false) MultipartFile madeWorkImage
    ) {
        CaseStudy saved = caseStudyService.saveCaseStudy(
                title, industry, category, overview, challenge, outcome, madeWork,
                challengePoints, constraints, approachSteps,
                heroImage, challengeImage, approachImage, madeWorkImage
        );
        return ResponseEntity.ok(saved);
    }
    // ✅ UPDATE Case Study by ID
@PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<CaseStudy> updateCaseStudy(
        @PathVariable Long id,
        @RequestParam String title,
        @RequestParam String industry,
        @RequestParam String category,
        @RequestParam(required = false) String overview,
        @RequestParam(required = false) String challenge,
        @RequestParam(required = false) String outcome,
        @RequestParam(required = false) String madeWork,
        @RequestParam(required = false) String challengePoints,
        @RequestParam(required = false) String constraints,
        @RequestParam(required = false) String approachSteps,
        @RequestParam(required = false) MultipartFile heroImage,
        @RequestParam(required = false) MultipartFile challengeImage,
        @RequestParam(required = false) MultipartFile approachImage,
        @RequestParam(required = false) MultipartFile madeWorkImage
) {
    CaseStudy updated = caseStudyService.updateCaseStudy(
            id, title, industry, category, overview, challenge, outcome, madeWork,
            challengePoints, constraints, approachSteps,
            heroImage, challengeImage, approachImage, madeWorkImage
    );
    return ResponseEntity.ok(updated);
}


    // ✅ GET all Case Studies
    @GetMapping
    public List<CaseStudy> getAllCaseStudies() {
        return caseStudyService.getAllCaseStudies();
    }

    // ✅ GET one Case Study by ID
    @GetMapping("/{id}")
    public ResponseEntity<CaseStudy> getCaseStudyById(@PathVariable Long id) {
        CaseStudy caseStudy = caseStudyService.getCaseStudyById(id);
        return caseStudy != null ? ResponseEntity.ok(caseStudy) : ResponseEntity.notFound().build();
    }

    // ✅ DELETE a Case Study
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCaseStudy(@PathVariable Long id) {
        caseStudyService.deleteCaseStudy(id);
        return ResponseEntity.noContent().build();
    }
}
