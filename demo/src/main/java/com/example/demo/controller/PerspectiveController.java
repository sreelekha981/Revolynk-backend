package com.example.demo.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.example.demo.entity.Perspective;
import com.example.demo.repository.PerspectiveRepository;

@RestController
@RequestMapping("/api/perspectives")
@CrossOrigin("*")
public class PerspectiveController {

    @Autowired
    private PerspectiveRepository repo;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<String> addPerspective(
    @RequestPart("perspective") Perspective perspective,
    @RequestPart(value = "image", required = false) MultipartFile imageFile) {

    try {
        if (imageFile != null && !imageFile.isEmpty()) {
            String uploadDir = System.getProperty("user.dir") + "/uploads";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String filePath = uploadDir + "/" + imageFile.getOriginalFilename();
            imageFile.transferTo(new File(filePath));
            perspective.setImagePath(filePath);
        }

        repo.save(perspective);
        return ResponseEntity.ok("Perspective saved successfully!");
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error saving perspective: " + e.getMessage());
    }
}


    @GetMapping
    public List<Perspective> getAll() {
        return repo.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.ok("🗑️ Deleted successfully!");
    }
}
