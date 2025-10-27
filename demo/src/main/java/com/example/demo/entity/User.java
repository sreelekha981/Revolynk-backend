package com.example.demo.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
@Column(nullable = false)

    private Integer failed_attempts=0;
    @Column(nullable = false)
    private Boolean account_locked=false;
    private String otp;
    private Long otp_expiry;
    private Timestamp created_at;
    //private String lastOtpSent;
    private Long lastOtpSentTime;
    private Long otpResendCount;
    // ✅ Add this field to store the JWT
    @Column(columnDefinition = "TEXT")
    private String token;

    // Getters and Setters
    public Long getId() { return id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public Boolean getAccount_locked() {
    return account_locked;
}

public void setAccount_locked(Boolean account_locked) {
    this.account_locked = account_locked;
}

public String getOtp() {
    return otp;
}

public void setOtp(String otp) {
    this.otp = otp;
}

public Long getOtp_expiry() {
    return otp_expiry;
}

public void setOtp_expiry(Long otp_expiry) {
    this.otp_expiry = otp_expiry;
}

public Timestamp getCreated_at() {
    return created_at;
}

public void setCreated_at(Timestamp created_at) {
    this.created_at = created_at;
}
public Integer getFailed_attempts() {
    return failed_attempts;
}

public void setFailed_attempts(Integer failed_attempts) {
    this.failed_attempts = failed_attempts;
}
public Long getLastOtpSentTime() {
        return lastOtpSentTime;
    }

    public void setLastOtpSentTime(Long lastOtpSentTime) {
        this.lastOtpSentTime = lastOtpSentTime;
    }

    public Long getOtpResendCount() {
        return otpResendCount;
    }

    public void setOtpResendCount(Long otpResendCount) {
        this.otpResendCount = otpResendCount;
    }

}
