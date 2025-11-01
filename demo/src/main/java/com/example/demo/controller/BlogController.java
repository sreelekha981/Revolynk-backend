package com.example.demo.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Blog;
import com.example.demo.repository.BlogRepository;
import com.example.demo.service.BlogService;

@RestController
@RequestMapping("/api/blogs")
@CrossOrigin("*")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogRepository blogRepository;

    // ✅ CREATE (with image upload)
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Blog> addBlog(@RequestPart("blog") Blog blog,
                                       @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                String uploadDir = System.getProperty("user.dir") + "/uploads";
                File uploadFolder = new File(uploadDir);
                if (!uploadFolder.exists()) uploadFolder.mkdirs();

                // Save file
                String fileName = imageFile.getOriginalFilename();
                String filePath = uploadDir + "/" + fileName;
                imageFile.transferTo(new File(filePath));

                // Store relative path only
                blog.setImagePath("uploads/" + fileName);
            }

            Blog saved = blogRepository.save(blog);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ✅ READ (all)
    @GetMapping
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    // ✅ READ (by ID)
    @GetMapping("/{id}")
    public ResponseEntity<?> getBlogById(@PathVariable Long id) {
        Optional<Blog> blog = blogRepository.findById(id);
        return blog.isPresent() ? ResponseEntity.ok(blog.get()) : ResponseEntity.notFound().build();
    }

    // ✅ UPDATE
    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateBlog(@PathVariable Long id,
                                             @RequestPart("blog") Blog updatedBlog,
                                             @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {
        try {
            Optional<Blog> existingBlogOpt = blogRepository.findById(id);
            if (existingBlogOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Blog not found");
            }

            Blog existingBlog = existingBlogOpt.get();

            existingBlog.setTitle(updatedBlog.getTitle());
            existingBlog.setTopic(updatedBlog.getTopic());
            existingBlog.setIndustry(updatedBlog.getIndustry());
            existingBlog.setDate(updatedBlog.getDate());
            existingBlog.setIntroParagraph(updatedBlog.getIntroParagraph());
            existingBlog.setParagraphAfterImage(updatedBlog.getParagraphAfterImage());

            if (imageFile != null && !imageFile.isEmpty()) {
                String uploadDir = System.getProperty("user.dir") + "/uploads";
                File uploadFolder = new File(uploadDir);
                if (!uploadFolder.exists()) uploadFolder.mkdirs();

                String fileName = imageFile.getOriginalFilename();
                String filePath = uploadDir + "/" + fileName;
                imageFile.transferTo(new File(filePath));

                existingBlog.setImagePath("uploads/" + fileName);
            }

            blogRepository.save(existingBlog);
            return ResponseEntity.ok("✅ Blog updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Error updating blog: " + e.getMessage());
        }
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        if (!blogRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        blogRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
