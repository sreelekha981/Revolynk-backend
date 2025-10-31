package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.JobApplication;
import com.example.demo.service.JobApplicationService;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin("*")
public class JobApplicationController {

    @Autowired
    private JobApplicationService service;

    @PostMapping
    public ResponseEntity<?> save(
            @RequestPart("data") JobApplication data,
            @RequestPart(value = "resume", required = false) MultipartFile resume
    ) {
        try {
            if (resume != null && !resume.isEmpty()) {
                String uploadDir = System.getProperty("user.dir") + File.separator + "uploads" + File.separator + "resumes" + File.separator;
                File dir = new File(uploadDir);
                if (!dir.exists()) dir.mkdirs();

                File dest = new File(uploadDir + resume.getOriginalFilename());
                resume.transferTo(dest);

                data.setResumeFile(dest.getAbsolutePath());
            }

            JobApplication saved = service.save(data);
            return ResponseEntity.ok(saved);

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error saving file: " + e.getMessage());
        }
    }

    @GetMapping
    public List<JobApplication> getAll() {
        return service.findAll();
    }
@GetMapping("/{id}")
public ResponseEntity<JobApplication> getApplicationById(@PathVariable Long id) {
    JobApplication app = service.findById(id);
    if (app != null) {
        return ResponseEntity.ok(app);
    } else {
        return ResponseEntity.notFound().build();
    }
}



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Application deleted successfully!");
    }
}
