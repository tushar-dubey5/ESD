package org.tushardubey.java.backend.service;
import org.tushardubey.java.backend.dto.StudentRequest;
import org.tushardubey.java.backend.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.tushardubey.java.backend.mapper.StudentMapper;
import org.tushardubey.java.backend.repo.StudentRepo;
import org.tushardubey.java.backend.entity.Student;
import org.tushardubey.java.backend.dto.LoginRequest;
import org.tushardubey.java.backend.repo.HostelRepo;
import org.tushardubey.java.backend.dto.StudentDetailsResponse;
import org.tushardubey.java.backend.entity.Hostel;


@Service
@RequiredArgsConstructor

public class StudentService {
    private final StudentRepo repo;
    private final StudentMapper mapper;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtService jwtService;
    private final HostelRepo hostelRepo;

    public String createStudent(StudentRequest request){
        Student student=mapper.toEntity(request);
        student.setPassword(passwordEncoder.encode(request.password()));
        repo.save(student);
        return "Created";
    }

    public String loginStudent(LoginRequest request) {
        Student student = repo.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(request.password(), student.getPassword())) {
            // Password is correct, generate JWT (next step)
            String token = jwtService.generateToken(student); // We'll implement this next
            return token; // Return JWT to the user
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
    public StudentDetailsResponse getStudentDetails(Long studentId) {
        Student student = repo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Hostel hostel = hostelRepo.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("Hostel details not found"));

        return StudentDetailsResponse.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .roomNumber(hostel.getRoomNumber())
                .hostelName(hostel.getName())
                .floor(hostel.getFloor())
                .build();
    }



}


