CREATE TABLE job_postings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    experience VARCHAR(100),
    location VARCHAR(255),
    type VARCHAR(100),
    date DATE,
    description TEXT,
    profile_points TEXT,   -- stores comma-separated or JSON list
    skills TEXT,           -- stores comma-separated or JSON list
    status VARCHAR(50)
);
