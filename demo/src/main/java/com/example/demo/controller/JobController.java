package com.example.demo.controller;

import java.util.List;
import java.util.Map;  // ✅ Add this

import org.springframework.beans.factory.annotation.Autowired;  // ✅ Add this
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Job;
import com.example.demo.service.JobPostingService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobController {

    @Autowired
    private JobPostingService jobService;

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        return jobService.getJobById(id)
            .map(job -> {
                if (job.getSkills() == null || job.getSkills().isEmpty()) {
                    job.setSkills("[]");
                }
                if (job.getProfilePoints() == null || job.getProfilePoints().isEmpty()) {
                    job.setProfilePoints("[]");
                }
                return ResponseEntity.ok(job);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Updated createJob method (handles arrays from frontend)
    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Map<String, Object> data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Job job = new Job();

            job.setTitle((String) data.get("title"));
            job.setExperience((String) data.get("experience"));
            job.setLocation((String) data.get("location"));
            job.setType((String) data.get("type"));
            job.setDate((String) data.get("date"));
            job.setDescription((String) data.get("description"));
            job.setStatus((String) data.get("status"));

            // Convert arrays (from frontend) into JSON strings
            job.setSkills(mapper.writeValueAsString(data.get("skills")));
            job.setProfilePoints(mapper.writeValueAsString(data.get("profilePoints")));

            Job savedJob = jobService.saveJob(job);
            return ResponseEntity.ok(savedJob);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();  // ✅ Corrected line
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job jobDetails) {
        return jobService.getJobById(id).map(job -> {
            job.setTitle(jobDetails.getTitle());
            job.setExperience(jobDetails.getExperience());
            job.setLocation(jobDetails.getLocation());
            job.setType(jobDetails.getType());
            job.setDate(jobDetails.getDate());
            job.setDescription(jobDetails.getDescription());
            job.setProfilePoints(jobDetails.getProfilePoints());
            job.setSkills(jobDetails.getSkills());
            job.setStatus(jobDetails.getStatus());
            jobService.saveJob(job);
            return ResponseEntity.ok(job);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }
}
