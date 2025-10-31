CREATE TABLE case_study (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255),
  industry VARCHAR(255),
  category VARCHAR(255),
  overview TEXT,
  challenge TEXT,
  outcome TEXT,
  made_work TEXT,
  challenge_points TEXT,
  constraints TEXT,
  approach_steps TEXT,
  hero_image VARCHAR(255),
  challenge_image VARCHAR(255),
  approach_image VARCHAR(255),
  made_work_image VARCHAR(255),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
