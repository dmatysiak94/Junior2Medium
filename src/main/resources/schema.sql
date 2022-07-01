DROP TABLE IF EXISTS student;

CREATE TABLE student(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(45) NOT NULL,
    email VARCHAR(60) NOT NULL,
    CONSTRAINT student_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS email_send_status;

CREATE TABLE  email_send_status
(
    id INT NOT NULL PRIMARY KEY REFERENCES email_send_status(id) ON DELETE CASCADE,
    date_sent TIMESTAMP DEFAULT NULL,
    is_enabled BOOLEAN DEFAULT TRUE
);