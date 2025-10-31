CREATE TABLE live_interview (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  interview_title VARCHAR(255),
  topic VARCHAR(255),
  industry VARCHAR(255),
  date DATE,
  image_path VARCHAR(500),
  quote TEXT,
  interviewee_name VARCHAR(255),
  interviewee_designation VARCHAR(255)
);

CREATE TABLE qa_section (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(255),
  content TEXT,
  live_interview_id BIGINT,
  CONSTRAINT fk_qa_live FOREIGN KEY (live_interview_id) REFERENCES live_interview(id) ON DELETE CASCADE
);

CREATE TABLE interview_question (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  question_text TEXT,
  answer_text TEXT,
  live_interview_id BIGINT,
  CONSTRAINT fk_q_live FOREIGN KEY (live_interview_id) REFERENCES live_interview(id) ON DELETE CASCADE
);
