package com.example.demo.controller;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.EmailService;

import jakarta.servlet.http.HttpServletRequest;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;
private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //private final String FIXED_EMAIL = "sreelekha.k@revolynk.com";
    //private final String FIXED_PASSWORD = "Revolynk@2023";
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
    String email = body.get("email");
    String password = body.get("password");

    if (email == null || password == null) {
        return ResponseEntity.badRequest().body(Map.of("message", "Email and password are required"));
    }

    Optional<User> optionalUser = userRepository.findByEmail(email);
    if (optionalUser.isEmpty()) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Invalid email or password"));
    }

    User user = optionalUser.get();
    String storedPassword = user.getPassword();

    // ✅ check bcrypt encoded passwords
    if (!passwordEncoder.matches(password, storedPassword)) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Invalid email or password"));
    }

    // ✅ generate new token
    String token = jwtUtil.generateToken(email);
    user.setToken(token);
    userRepository.save(user);

    return ResponseEntity.ok(Map.of(
        "message", "Login successful",
        "token", token
    ));
}

//     @PostMapping("/login")
// public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
//     String email = body.get("email");
//     String password = body.get("password");

//     if (email == null || password == null) {
//         return ResponseEntity.badRequest().body("Email and password are required");
//     }

//     // ✅ Step 1: Validate against fixed credentials
//     if (!FIXED_EMAIL.equalsIgnoreCase(email) || !FIXED_PASSWORD.equals(password)) {
//         return ResponseEntity.badRequest().body("Invalid email or password");
//     }

//     // ✅ Step 2: Check if user exists in DB
//     Optional<User> optionalUser = userRepository.findByEmail(email);
//     User user;

//     if (optionalUser.isPresent()) {
//         // User already exists — reuse
//         user = optionalUser.get();
//     } else {
//         // ✅ Step 3: Create new user on first login
//         user = new User();
//         user.setEmail(email);
//         user.setPassword(password); // optional: hash it if needed
//     }

//     // ✅ Step 4: Generate new token each time
//     String token = jwtUtil.generateToken(email);
//     user.setToken(token);
//     user.setToken(token);
// if (user.getAccount_locked() == null) {
//     user.setAccount_locked(false);
// }
// if (user.getFailed_attempts() == null) {
//     user.setFailed_attempts(0);
    
// }

// userRepository.save(user);


//     // ✅ Step 5: Save user (only first time creates a record)

//     // ✅ Step 6: Return token to frontend or Postman
//     return ResponseEntity.ok(Map.of("token", token));
    
// }
// @PostMapping("/login")
//     public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
//         String email = body.get("email");
//         String password = body.get("password");

//         if (email == null || password == null) {
//             return ResponseEntity.badRequest().body("Email and password are required");
//         }

//         Optional<User> optionalUser = userRepository.findByEmail(email);
//         if (optionalUser.isEmpty()) {
//             return ResponseEntity.badRequest().body("Email not registered");
//         }

//         User user = optionalUser.get();
//         String stored = user.getPassword();

//         if (stored == null || stored.isEmpty()) {
//             return ResponseEntity.badRequest().body("User has no password set");
//         }

//         boolean ok = false;

//         // Normal case: stored is bcrypt hash
//         try {
//             if (passwordEncoder.matches(password, stored)) {
//                 ok = true;
//             }
//         } catch (Exception e) {
//             // unusual -- continue to fallback
//         }

//         // Fallback: maybe stored password is plain text (legacy). Compare and upgrade.
//         if (!ok && stored.equals(password)) {
//             ok = true;
//             // upgrade: encode and save new bcrypt hash
//             user.setPassword(passwordEncoder.encode(password));
//             userRepository.save(user);
//         }

//         if (!ok) {
//             return ResponseEntity.badRequest().body("Invalid email or password");
//         }

//         // success: create token, set and save
//         String token = jwtUtil.generateToken(email);
//         user.setToken(token);
//         userRepository.save(user);

//         return ResponseEntity.ok(Map.of("token", token));
// //     }
// @PostMapping("/forgot-password")
// public ResponseEntity<?> sendOtp(@RequestParam String email) {
//     System.out.println("📩 Forgot password request received for: " + email);

//     Optional<User> userOpt = userRepository.findByEmail(email);
//     if (userOpt.isEmpty()) {
//         System.out.println("❌ Email not registered: " + email);
//         return ResponseEntity.badRequest().body("Email not registered");
//     }

//     User user = userOpt.get();
//     long now = System.currentTimeMillis();

//     if (user.getLastOtpSentTime() != null && user.getOtpResendCount() >= 3 &&
//         now - user.getLastOtpSentTime() < 60 * 60 * 1000) {
//         System.out.println("⚠️ OTP resend limit reached for: " + email);
//         return ResponseEntity.badRequest().body("OTP resend limit reached. Try again later.");
//     }

//     String otp = String.format("%06d", new Random().nextInt(999999));
//     user.setOtp(otp);
//     user.setOtp_expiry(now + 10 * 60 * 1000);
//     user.setOtpResendCount(user.getOtpResendCount() + 1);
//     user.setLastOtpSentTime(now);
//     userRepository.save(user);

//     // ✅ Add if–then to check before sending
//     if (email != null && !email.isEmpty()) {
//         System.out.println(">>> Attempting to send OTP to: " + email);
//         emailService.sendOtpEmail(user.getEmail(), otp);
//         System.out.println("✅ OTP email sent trigger executed for: " + email);
//     } else {
//         System.out.println("⚠️ Email is null or empty, skipping email sending.");
//         System.out.println("Generated OTP: " + otp);
// System.out.println("Sending OTP to: " + user.getEmail());

//     }

//     return ResponseEntity.ok("OTP has been sent to your email");
    
// }
@PostMapping("/forgot-password")
public ResponseEntity<?> sendOtp(@RequestParam String email) {
    System.out.println("📩 Forgot password request received for: " + email);

    Optional<User> userOpt = userRepository.findByEmail(email);
    if (userOpt.isEmpty()) {
        System.out.println("❌ Email not registered: " + email);
        return ResponseEntity.badRequest().body("Email not registered");
    }

    User user = userOpt.get();
    long now = System.currentTimeMillis();

    // ⚠️ 1️⃣ Prevent sending OTP too frequently (less than 30 seconds)
    if (user.getLastOtpSentTime() != null && (now - user.getLastOtpSentTime() < 30 * 1000)) {
        long secondsLeft = (30 * 1000 - (now - user.getLastOtpSentTime())) / 1000;
        return ResponseEntity.badRequest().body("Please wait " + secondsLeft + " seconds before requesting another OTP.");
    }

    // ⚠️ 2️⃣ Enforce resend limit (3 per hour)
    if (user.getLastOtpSentTime() != null && user.getOtpResendCount() >= 3 &&
        now - user.getLastOtpSentTime() < 60 * 60 * 1000) {
        System.out.println("⚠️ OTP resend limit reached for: " + email);
        return ResponseEntity.badRequest().body("OTP resend limit reached. Try again later.");
    }

    // ✅ 3️⃣ Generate new OTP
    String otp = String.format("%06d", new Random().nextInt(999999));
    user.setOtp(otp);
    user.setOtp_expiry(now + 10 * 60 * 1000); // valid 10 minutes
    user.setLastOtpSentTime(now);

    // ✅ Reset resend count after 1 hour
    if (user.getLastOtpSentTime() == null || now - user.getLastOtpSentTime() > 60 * 60 * 1000) {
        user.setOtpResendCount(1L);
    } else {
        user.setOtpResendCount(user.getOtpResendCount() + 1);
    }

    userRepository.save(user);

    // ✅ Send OTP via email
    if (email != null && !email.isEmpty()) {
        System.out.println(">>> Sending OTP to: " + email);
        emailService.sendOtpEmail(user.getEmail(), otp);
        System.out.println("✅ OTP sent successfully to: " + email);
    }

    return ResponseEntity.ok("OTP sent successfully. You can request a new OTP after 30 seconds.");
}


    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) return ResponseEntity.badRequest().body("Email not registered");

        User user = userOpt.get();
        if (user.getOtp() == null || !user.getOtp().equals(otp)) return ResponseEntity.badRequest().body("Invalid OTP");
        if (System.currentTimeMillis() > user.getOtp_expiry()) return ResponseEntity.badRequest().body("OTP expired");

        return ResponseEntity.ok("OTP verified successfully");
    }

    @PostMapping("/change-password")
public ResponseEntity<?> changePassword(@RequestParam String email,
                                        @RequestParam String newPassword,
                                        @RequestParam String confirmPassword) {
    if (!newPassword.equals(confirmPassword))
        return ResponseEntity.badRequest().body("Passwords do not match");

    if (!newPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$"))
        return ResponseEntity.badRequest().body("Password must be 8+ chars with uppercase, lowercase, number, special char");

    Optional<User> userOpt = userRepository.findByEmail(email);
    if (userOpt.isEmpty()) return ResponseEntity.badRequest().body("Email not registered");

    User user = userOpt.get();
    user.setPassword(passwordEncoder.encode(newPassword)); // ✅ encodes the new password
    user.setOtp(null);
    user.setOtp_expiry(null);
    user.setOtpResendCount(0L);
    
    userRepository.save(user); // ✅ should persist change
    return ResponseEntity.ok("Password changed successfully");
}



@PostMapping("/logout")
public ResponseEntity<?> logout(HttpServletRequest request) {
    String authHeader = request.getHeader("Authorization");

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Missing or invalid token"));
    }

    String token = authHeader.substring(7);
    String email;

    try {
        // ✅ Extract email from JWT instead of generating a new one
        email = jwtUtil.extractEmail(token);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", "Invalid token: " + e.getMessage()));
    }

    Optional<User> optionalUser = userRepository.findByEmail(email);
    if (optionalUser.isEmpty()) {
        return ResponseEntity.badRequest().body(Map.of("message", "User not found"));
    }

    User user = optionalUser.get();
    user.setToken(null);
    userRepository.save(user);

    System.out.println("✅ Logged out: " + email);
    return ResponseEntity.ok(Map.of("message", "Logout successful"));
}
}