package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String to, String otp) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            //message.setFrom("sreelekha.k@revolynk.com"); // MUST match spring.mail.username
            message.setTo(to);
            message.setSubject("Your OTP Code");
            message.setText("Your OTP code is: " + otp + "\n\nThis code will expire in 10 minutes.");

            mailSender.send(message);

            System.out.println("✅ OTP email sent successfully to " + to);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Failed to send OTP email: " + e.getMessage());
        }
    }
}
