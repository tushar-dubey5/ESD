-- Create the 'student' table
CREATE TABLE student (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         first_name VARCHAR(50) NOT NULL,
                         last_name VARCHAR(50) NOT NULL,
                         email VARCHAR(100) UNIQUE NOT NULL,
                         password VARCHAR(255) NOT NULL
);

-- Create the 'hostel' table
CREATE TABLE hostel (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        floor INT NOT NULL,
                        name VARCHAR(100) NOT NULL,
                        room_number INT NOT NULL,
                        student_id BIGINT
);

-- Create the 'swap_application' table
CREATE TABLE swap_application (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  application_message TEXT,
                                  recipient_message TEXT,
                                  status ENUM('PENDING', 'ACCEPTED', 'REJECTED') DEFAULT 'PENDING',
                                  applicant_id BIGINT,
                                  recipient_id BIGINT
);
