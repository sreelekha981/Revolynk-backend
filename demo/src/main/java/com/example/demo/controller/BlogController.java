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
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestPart;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import com.example.demo.entity.Blog;
// import com.example.demo.repository.BlogRepository;
// import com.example.demo.service.BlogService;
// import com.google.api.client.util.Value;

// @RestController
// @RequestMapping("/api/blogs/add")
// @CrossOrigin("*")

// public class BlogController {

//     @Value("${file.upload-dir}")
// private String uploadDir;

//     @Autowired
//     private BlogService blogService;
//     @Autowired
//     private BlogRepository blogRepository;
// //String uploadDir = new File("uploads").getAbsolutePath();

//     @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
// public ResponseEntity<String> addBlog(@RequestPart("blog") Blog blog,
//                                       @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {
//     try {
//         if (imageFile != null && !imageFile.isEmpty()) {
//             // Create upload directory if not exists
//             String uploadDir = System.getProperty("user.dir") + "/uploads";
//             File uploadFolder = new File(uploadDir);
//             if (!uploadFolder.exists()) {
//                 uploadFolder.mkdirs();  // ✅ creates the folder automatically
//             }

//             // Save the image
//             String filePath = uploadDir + "/" + imageFile.getOriginalFilename();
//             imageFile.transferTo(new File(filePath));

//             blog.setImagePath(filePath); // optional: store path in DB
//         }

//         blogRepository.save(blog);
//         return ResponseEntity.ok("✅ Blog saved successfully!");
//     } catch (Exception e) {
//         e.printStackTrace();
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                              .body("❌ Error saving blog: " + e.getMessage());
//     }
// }


//     @GetMapping
//     public List<Blog> getAllBlogs() {
//         return blogService.getAllBlogs();
//     }

//     @GetMapping("/{id}")
//     public Blog getBlogById(@PathVariable Long id) {
//         return blogService.getBlogById(id);
//     }

//     @DeleteMapping("/{id}")
//     public void deleteBlog(@PathVariable Long id) {
//         blogService.deleteBlog(id);
//     }
// }
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
@RequestMapping("/api/blogs/add")
@CrossOrigin("*")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogRepository blogRepository;

    // ✅ CREATE
   @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<Blog> addBlog(@RequestPart("blog") Blog blog,
                                   @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {
    try {
        if (imageFile != null && !imageFile.isEmpty()) {
            String uploadDir = System.getProperty("user.dir") + "/uploads";
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) uploadFolder.mkdirs();
            String filePath = uploadDir + "/" + imageFile.getOriginalFilename();
            imageFile.transferTo(new File(filePath));
            blog.setImagePath(filePath);
        }
        Blog saved = blogRepository.save(blog);
        return ResponseEntity.ok(saved); // ✅ returns id and other fields
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

    // ✅ READ (GET ALL)
    @GetMapping
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    // ✅ READ (GET BY ID)
    @GetMapping("/{id}")
    public ResponseEntity<?> getBlogById(@PathVariable Long id) {
        Optional<Blog> blog = blogRepository.findById(id);
        return blog.isPresent() ? ResponseEntity.ok(blog.get()) : ResponseEntity.notFound().build();
    }

    // ✅ UPDATE (EDIT)
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

            // Update text fields
            existingBlog.setTitle(updatedBlog.getTitle());
            existingBlog.setTopic(updatedBlog.getTopic());
            existingBlog.setIndustry(updatedBlog.getIndustry());
            existingBlog.setDate(updatedBlog.getDate());
            existingBlog.setIntroParagraph(updatedBlog.getIntroParagraph());
            existingBlog.setParagraphAfterImage(updatedBlog.getParagraphAfterImage());

            // Update image if new one uploaded
            if (imageFile != null && !imageFile.isEmpty()) {
                String uploadDir = System.getProperty("user.dir") + "/uploads";
                File uploadFolder = new File(uploadDir);
                if (!uploadFolder.exists()) uploadFolder.mkdirs();

                String filePath = uploadDir + "/" + imageFile.getOriginalFilename();
                imageFile.transferTo(new File(filePath));
                existingBlog.setImagePath(filePath);
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
