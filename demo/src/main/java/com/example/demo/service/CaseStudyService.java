package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.CaseStudy;
import com.example.demo.repository.CaseStudyRepository;

@Service
public class CaseStudyService {

    @Autowired
    private CaseStudyRepository caseStudyRepository;

    // Directory to save uploaded images
    private static final String UPLOAD_DIR = "uploads/";

    // ✅ CREATE / SAVE case study
    public CaseStudy saveCaseStudy(String title,
                                   String industry,
                                   String category,
                                   String overview,
                                   String challenge,
                                   String outcome,
                                   String madeWork,
                                   String challengePoints,
                                   String constraints,
                                   String approachSteps,
                                   MultipartFile heroImage,
                                   MultipartFile challengeImage,
                                   MultipartFile approachImage,
                                   MultipartFile madeWorkImage) {

        try {
            CaseStudy caseStudy = new CaseStudy();
            caseStudy.setTitle(title);
            caseStudy.setIndustry(industry);
            caseStudy.setCategory(category);
            caseStudy.setOverview(overview);
            caseStudy.setChallenge(challenge);
            caseStudy.setOutcome(outcome);
            caseStudy.setMadeWork(madeWork);
            caseStudy.setChallengePoints(challengePoints);
            caseStudy.setConstraints(constraints);
            caseStudy.setApproachSteps(approachSteps);

            // Save uploaded images and store file paths
            caseStudy.setHeroImage(saveFile(heroImage));
            caseStudy.setChallengeImage(saveFile(challengeImage));
            caseStudy.setApproachImage(saveFile(approachImage));
            caseStudy.setMadeWorkImage(saveFile(madeWorkImage));

            // Set creation timestamp
            caseStudy.setCreatedAt(LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            return caseStudyRepository.save(caseStudy);

        } catch (Exception e) {
            throw new RuntimeException("Error saving case study", e);
        }
    }

    // ✅ HELPER: Save uploaded files to local folder
   private String saveFile(MultipartFile file) throws IOException {
    if (file == null || file.isEmpty()) {
        return null;
    }

    // Define a permanent uploads directory (e.g., under "uploads" in project root)
    String uploadDir = System.getProperty("user.dir") + "/uploads/";

    // Create directory if missing
    File directory = new File(uploadDir);
    if (!directory.exists()) {
        directory.mkdirs(); // ✅ creates folder if it doesn’t exist
    }

    // Create unique filename
    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
    String filePath = uploadDir + fileName;

    // Save the file
    file.transferTo(new File(filePath));

    // Return the relative path (for database storage or frontend)
    return "/uploads/" + fileName;
}

    // ✅ GET all case studies
    public List<CaseStudy> getAllCaseStudies() {
        return caseStudyRepository.findAll();
    }

    // ✅ GET one case study by ID
    public CaseStudy getCaseStudyById(Long id) {
        return caseStudyRepository.findById(id).orElse(null);
    }

    // ✅ DELETE a case study by ID
    public void deleteCaseStudy(Long id) {
        caseStudyRepository.deleteById(id);
    }
    public CaseStudy updateCaseStudy(Long id,
        String title, String industry, String category,
        String overview, String challenge, String outcome, String madeWork,
        String challengePoints, String constraints, String approachSteps,
        MultipartFile heroImage, MultipartFile challengeImage,
        MultipartFile approachImage, MultipartFile madeWorkImage) {

    CaseStudy existing = caseStudyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("CaseStudy not found with id " + id));

    existing.setTitle(title);
    existing.setIndustry(industry);
    existing.setCategory(category);
    existing.setOverview(overview);
    existing.setChallenge(challenge);
    existing.setOutcome(outcome);
    existing.setMadeWork(madeWork);
    existing.setChallengePoints(challengePoints);
    existing.setConstraints(constraints);
    existing.setApproachSteps(approachSteps);

    try {
        if (heroImage != null && !heroImage.isEmpty()) {
            existing.setHeroImage(saveFile(heroImage));
        }
        if (challengeImage != null && !challengeImage.isEmpty()) {
            existing.setChallengeImage(saveFile(challengeImage));
        }
        if (approachImage != null && !approachImage.isEmpty()) {
            existing.setApproachImage(saveFile(approachImage));
        }
        if (madeWorkImage != null && !madeWorkImage.isEmpty()) {
            existing.setMadeWorkImage(saveFile(madeWorkImage));
        }
    } catch (IOException e) {
        throw new RuntimeException("Error updating case study", e);
    }

    return caseStudyRepository.save(existing);
}

}

