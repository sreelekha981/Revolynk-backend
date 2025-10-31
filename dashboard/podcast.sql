-- ===========================
-- Table: podcasts
-- ===========================
CREATE TABLE podcasts (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    subtitle VARCHAR(350) NOT NULL,
    topic VARCHAR(100) NOT NULL,
    industry VARCHAR(100) NOT NULL,
    date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ===========================
-- Table: podcast_episodes
-- ===========================
CREATE TABLE podcast_episodes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    episode_title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    audio_path VARCHAR(500),
    podcast_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_podcast_episode FOREIGN KEY (podcast_id)
        REFERENCES podcasts(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
