-- Set 'student_id' in 'hostel' table as a foreign key to 'id' in 'student' table
ALTER TABLE hostel
    ADD CONSTRAINT fk_hostel_student
        FOREIGN KEY (student_id)
            REFERENCES student(id)
            ON DELETE SET NULL;

-- Set 'applicant_id' and 'recipient_id' in 'swap_application' table as foreign keys to 'id' in 'student' table
ALTER TABLE swap_application
    ADD CONSTRAINT fk_swap_applicant
        FOREIGN KEY (applicant_id)
            REFERENCES student(id)
            ON DELETE CASCADE;

ALTER TABLE swap_application
    ADD CONSTRAINT fk_swap_recipient
        FOREIGN KEY (recipient_id)
            REFERENCES student(id)
            ON DELETE CASCADE;
