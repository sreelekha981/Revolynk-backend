// package com.example.demo.controller;

// import java.io.File;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestPart;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import com.example.demo.entity.NewsArticle;
// import com.example.demo.repository.NewsArticleRepository;
// import com.example.demo.service.NewsArticleService;

// @RestController
// @RequestMapping("/api/news")
// @CrossOrigin("*")
// public class NewsArticleController {

//     @Autowired
//     private NewsArticleService service;

//     @Autowired
//     private NewsArticleRepository repo;

//     @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//     public ResponseEntity<String> addArticle(@RequestPart("article") NewsArticle article,
//                                              @RequestPart(value = "image", required = false) MultipartFile imageFile) {
//         try {
//             if (imageFile != null && !imageFile.isEmpty()) {
//                 String uploadDir = System.getProperty("user.dir") + "/uploads";
//                 File dir = new File(uploadDir);
//                 if (!dir.exists()) dir.mkdirs();

//                 String filePath = uploadDir + "/" + imageFile.getOriginalFilename();
//                 imageFile.transferTo(new File(filePath));
//                 article.setImagePath(filePath);
//             }

//             repo.save(article);
//             return ResponseEntity.ok("✅ Article saved successfully!");
//         } catch (Exception e) {
//             e.printStackTrace();
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                     .body("❌ Error saving article: " + e.getMessage());
//         }
//     }

//     @GetMapping
//     public List<NewsArticle> getAll() {
//         return service.getAll();
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<String> delete(@PathVariable Long id) {
//         service.deleteById(id);
//         return ResponseEntity.ok("🗑️ Deleted successfully!");
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<String> update(@PathVariable Long id, @RequestBody NewsArticle updatedArticle) {
//         NewsArticle existing = service.getById(id);
//         if (existing == null) {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Article not found!");
//         }

//         updatedArticle.setId(id);
//         repo.save(updatedArticle);
//         return ResponseEntity.ok("✅ Article updated successfully!");
//     }
// }
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.NewsArticle;
import com.example.demo.repository.NewsArticleRepository;
import com.example.demo.service.NewsArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/news")
@CrossOrigin("*")
public class NewsArticleController {

    @Autowired
    private NewsArticleService service;

    @Autowired
    private NewsArticleRepository repo;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addArticle(
            @RequestPart("article") String articleJson,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) {

        try {
            NewsArticle article = objectMapper.readValue(articleJson, NewsArticle.class);

            if (imageFile != null && !imageFile.isEmpty()) {
                String uploadDir = System.getProperty("user.dir") + "/uploads";
                File dir = new File(uploadDir);
                if (!dir.exists()) dir.mkdirs();

                String filePath = uploadDir + "/" + imageFile.getOriginalFilename();
                imageFile.transferTo(new File(filePath));
                article.setImagePath(filePath);
            }

            repo.save(article);
            return ResponseEntity.ok("✅ Article saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Error saving article: " + e.getMessage());
        }
    }

    @GetMapping
    public List<NewsArticle> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("🗑️ Deleted successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody NewsArticle updatedArticle) {
        NewsArticle existing = service.getById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Article not found!");
        }

        updatedArticle.setId(id);
        repo.save(updatedArticle);
        return ResponseEntity.ok("✅ Article updated successfully!");
    }
}
