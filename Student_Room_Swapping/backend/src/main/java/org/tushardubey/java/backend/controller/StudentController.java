package org.tushardubey.java.backend.controller;

import org.springframework.web.bind.annotation.*;
import org.tushardubey.java.backend.dto.StudentRequest;
import org.tushardubey.java.backend.service.StudentService;
import org.tushardubey.java.backend.dto.LoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.tushardubey.java.backend.dto.StudentDetailsResponse;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/student/auth")
public class StudentController {
    private final StudentService studentService;
    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid StudentRequest request){
        return ResponseEntity.ok(studentService.createStudent(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginStudent(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(studentService.loginStudent(request));
    }
    @GetMapping("/about-me")
    public ResponseEntity<StudentDetailsResponse> getStudentDetails(@RequestParam Long studentId) {
        return ResponseEntity.ok(studentService.getStudentDetails(studentId));
    }
}
