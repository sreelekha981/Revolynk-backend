CREATE TABLE perspective (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    article_title VARCHAR(255),
    subtitle TEXT,
    topic VARCHAR(255),
    industry VARCHAR(255),
    date DATE,
    brief_section_content JSON,
    brief_key_insight JSON,
    section_title JSON,
    section_content JSON,
    accordion_title JSON,
    accordion_description JSON,
    image_path VARCHAR(500)
);
