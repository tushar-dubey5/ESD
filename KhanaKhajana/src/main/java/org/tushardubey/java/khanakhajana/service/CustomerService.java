package org.tushardubey.java.khanakhajana.service;

import org.tushardubey.java.khanakhajana.dto.CustomerRequest;
import org.tushardubey.java.khanakhajana.dto.CustomerResponse;
import org.tushardubey.java.khanakhajana.entity.Customer;
import org.tushardubey.java.khanakhajana.jwt.JwtService;
import org.tushardubey.java.khanakhajana.mapper.CustomerMapper;
import org.tushardubey.java.khanakhajana.repo.CustomerRepo;
import org.tushardubey.java.khanakhajana.dto.LoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
@RequiredArgsConstructor

public class CustomerService {
    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtService jwtService;

    public String createCustomer(CustomerRequest request){
        Customer customer=mapper.toEntity(request);
        customer.setPassword(passwordEncoder.encode(request.password()));
        repo.save(customer);
        return "Created";
    }

    public String loginCustomer(LoginRequest request) {
        Customer customer = repo.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(request.password(), customer.getPassword())) {
            // Password is correct, generate JWT (next step)
            String token = jwtService.generateToken(customer); // We'll implement this next
            return token; // Return JWT to the user
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}

