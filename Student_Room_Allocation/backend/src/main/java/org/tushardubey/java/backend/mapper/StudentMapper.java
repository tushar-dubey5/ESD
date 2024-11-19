package org.tushardubey.java.backend.mapper;

import org.springframework.stereotype.Service;
import org.tushardubey.java.backend.dto.StudentRequest;
import org.tushardubey.java.backend.entity.Student;
@Service
public class StudentMapper {
    public Student toEntity(StudentRequest request) {
        return Student.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password()) // Note: Password encoding handled in service layer
                .build();
    }
}
