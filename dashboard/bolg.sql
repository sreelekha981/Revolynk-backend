CREATE TABLE blogs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    topic VARCHAR(255),
    industry VARCHAR(255),
    date DATE,
    intro_paragraph TEXT,
    image_path VARCHAR(255),
    paragraph_after_image TEXT
);
CREATE TABLE blog_sections (
    blog_id BIGINT,
    section_title VARCHAR(255),
    section_content TEXT,
    FOREIGN KEY (blog_id) REFERENCES blogs(id) ON DELETE CASCADE
);
CREATE TABLE blog_accordions (
    blog_id BIGINT,
    accordion_title VARCHAR(255),
    accordion_description TEXT,
    FOREIGN KEY (blog_id) REFERENCES blogs(id) ON DELETE CASCADE
);
CREATE TABLE blog_authors (
    blog_id BIGINT,
    author_name VARCHAR(255),
    designation VARCHAR(255),
    linked_in_url VARCHAR(255),
    FOREIGN KEY (blog_id) REFERENCES blogs(id) ON DELETE CASCADE
);
