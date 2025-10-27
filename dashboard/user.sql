CREATE DATABASE IF NOT EXISTS revolynkdb;
USE revolynkdb;

-- Table: users (for login system)
CREATE TABLE users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  failed_attempts INT DEFAULT 0,
  account_locked BOOLEAN DEFAULT FALSE,
  otp VARCHAR(10),
  otp_expiry BIGINT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);