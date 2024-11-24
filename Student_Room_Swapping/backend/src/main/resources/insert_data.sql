-- Insert sample data into 'student' table
INSERT INTO student (first_name, last_name, email, password) VALUES
                                                                 ('John', 'Doe', 'john.doe@example.com', 'hashed_password_123'),
                                                                 ('Jane', 'Smith', 'jane.smith@example.com', 'hashed_password_456'),
                                                                 ('Alice', 'Johnson', 'alice.johnson@example.com', 'hashed_password_789'),
                                                                 ('Bob', 'Brown', 'bob.brown@example.com', 'hashed_password_101');

-- Insert sample data into 'hostel' table
INSERT INTO hostel (floor, name, room_number, student_id) VALUES
                                                              (1, 'Hostel A', 101, 1),
                                                              (1, 'Hostel A', 102, 2),
                                                              (2, 'Hostel B', 201, 3),
                                                              (2, 'Hostel B', 202, 4);

-- Insert sample data into 'swap_application' table
INSERT INTO swap_application (application_message, recipient_message, status, applicant_id, recipient_id) VALUES
                                                                                                              ('I would like to swap rooms with you.', 'Let me think about it.', 'PENDING', 1, 2),
                                                                                                              ('Can we swap rooms for better convenience?', 'Sure, let’s proceed.', 'ACCEPTED', 3, 4),
                                                                                                              ('Looking for a swap due to personal reasons.', NULL, 'PENDING', 2, 1);
