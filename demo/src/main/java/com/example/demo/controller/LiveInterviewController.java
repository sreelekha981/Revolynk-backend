// package com.example.demo.controller;

// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.nio.file.StandardCopyOption;
// import java.util.List;

// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestPart;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import com.example.demo.entity.LiveInterview;
// import com.example.demo.service.LiveInterviewService;

// @RestController
// @RequestMapping("/api/live-interviews")
// @CrossOrigin(origins = "*")
// public class LiveInterviewController {

//     private final LiveInterviewService service;

//     public LiveInterviewController(LiveInterviewService service) {
//         this.service = service;
//     }
// // @PostMapping
// // public ResponseEntity<LiveInterview> create(@RequestBody LiveInterview liveInterview) {
// //     return ResponseEntity.ok(service.save(liveInterview));
// // }
//     @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//     public ResponseEntity<?> create(
//             @RequestPart("liveInterview") LiveInterview liveInterview,
//             @RequestPart(value = "image", required = false) MultipartFile image
//     ) throws IOException {

//         // handle image if present
//         if (image != null && !image.isEmpty()) {
//             String path = saveFile(image);
//             liveInterview.setImagePath(path);
//         }

//         LiveInterview saved = service.save(liveInterview);
//         return ResponseEntity.ok(saved);
//     }

//     @GetMapping
//     public List<LiveInterview> list() {
//         return service.getAll();
//     }

//     private String saveFile(MultipartFile file) throws IOException {
//         Path uploadDir = Paths.get("uploads");
//         if (!Files.exists(uploadDir)) Files.createDirectories(uploadDir);
//         String filename = System.currentTimeMillis() + "_" + Path.of(file.getOriginalFilename()).getFileName().toString();
//         Path target = uploadDir.resolve(filename);
//         Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
//         return target.toString();
//     }
    
// }
package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.LiveInterview;
import com.example.demo.service.LiveInterviewService;

@RestController
@RequestMapping("/api/live-interviews")
@CrossOrigin(origins = "*")
public class LiveInterviewController {

    private final LiveInterviewService service;

    public LiveInterviewController(LiveInterviewService service) {
        this.service = service;
    }

    // ✅ CREATE new interview
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create(
            @RequestPart("liveInterview") LiveInterview liveInterview,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) throws IOException {

        if (image != null && !image.isEmpty()) {
            String path = saveFile(image);
            liveInterview.setImagePath(path);
        }

        LiveInterview saved = service.save(liveInterview);
        return ResponseEntity.ok(saved);
    }

    // ✅ READ all interviews
    @GetMapping
    public List<LiveInterview> list() {
        return service.getAll();
    }

    // ✅ READ single interview by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<LiveInterview> interview = service.getById(id);
        return interview.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    // ✅ UPDATE existing interview (with optional new image)
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestPart("liveInterview") LiveInterview updatedInterview,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) throws IOException {

        Optional<LiveInterview> existingOpt = service.getById(id);
        if (existingOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Interview not found");
        }

        LiveInterview existing = existingOpt.get();

        // Update fields
        existing.setInterviewTitle(updatedInterview.getInterviewTitle());
        existing.setTopic(updatedInterview.getTopic());
        existing.setIndustry(updatedInterview.getIndustry());
        existing.setDate(updatedInterview.getDate());
        existing.setQuote(updatedInterview.getQuote());
        existing.setIntervieweeName(updatedInterview.getIntervieweeName());
        existing.setIntervieweeDesignation(updatedInterview.getIntervieweeDesignation());
        existing.setQaSections(updatedInterview.getQaSections());
        existing.setQuestions(updatedInterview.getQuestions());

        // Handle image update
        if (image != null && !image.isEmpty()) {
            String path = saveFile(image);
            existing.setImagePath(path);
        }

        LiveInterview saved = service.save(existing);
        return ResponseEntity.ok(saved);
    }

    // ✅ DELETE interview
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        boolean deleted = service.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok("Interview deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Interview not found");
        }
    }

    // Utility method for saving uploaded images
    private String saveFile(MultipartFile file) throws IOException {
        Path uploadDir = Paths.get("uploads");
        if (!Files.exists(uploadDir)) Files.createDirectories(uploadDir);
        String filename = System.currentTimeMillis() + "_" + Path.of(file.getOriginalFilename()).getFileName().toString();
        Path target = uploadDir.resolve(filename);
        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        return target.toString();
    }
}
