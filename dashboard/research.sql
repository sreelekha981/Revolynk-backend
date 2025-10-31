

-- =========================
-- 1️⃣ Table: research
-- =========================
CREATE TABLE research (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    topic VARCHAR(255),
    industry VARCHAR(255),
    date DATE,
    abstract_text TEXT,
    document_path VARCHAR(500)
);

-- =========================
-- 2️⃣ Table: authors
-- =========================
CREATE TABLE authors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    designation VARCHAR(255),
    linkedin_url VARCHAR(500),
    research_id BIGINT,
    CONSTRAINT fk_author_research FOREIGN KEY (research_id)
        REFERENCES research(id) ON DELETE CASCADE
);

-- =========================
-- 3️⃣ Table: key_findings
-- =========================
CREATE TABLE key_findings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    key_finding TEXT,
    research_id BIGINT,
    CONSTRAINT fk_finding_research FOREIGN KEY (research_id)
        REFERENCES research(id) ON DELETE CASCADE
);

-- =========================
-- 4️⃣ Table: content_blocks
-- =========================
CREATE TABLE content_blocks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    content TEXT,
    research_id BIGINT,
    CONSTRAINT fk_block_research FOREIGN KEY (research_id)
        REFERENCES research(id) ON DELETE CASCADE
);

-- =========================
-- 5️⃣ Table: accordion_sections
-- =========================
CREATE TABLE accordion_sections (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    content TEXT,
    research_id BIGINT,
    CONSTRAINT fk_accordion_research FOREIGN KEY (research_id)
        REFERENCES research(id) ON DELETE CASCADE
);
