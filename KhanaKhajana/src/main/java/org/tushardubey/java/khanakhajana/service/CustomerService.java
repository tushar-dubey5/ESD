package org.tushardubey.java.khanakhajana.service;

import org.tushardubey.java.khanakhajana.dto.CustomerRequest;
import org.tushardubey.java.khanakhajana.dto.CustomerResponse;
import org.tushardubey.java.khanakhajana.entity.Customer;
import org.tushardubey.java.khanakhajana.jwt.JwtService;
import org.tushardubey.java.khanakhajana.mapper.CustomerMapper;
import org.tushardubey.java.khanakhajana.repo.CustomerRepo;
import org.tushardubey.java.khanakhajana.dto.LoginRequest;
import org.tushardubey.java.khanakhajana.dto.UpdateRequest;
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
    public void updateCustomer(String token, UpdateRequest updateRequest) {
        // Extract email from token
        String email = jwtService.getEmailFromToken(token.replace("Bearer ", ""));

        // Find customer by email
        Customer customer = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Update fields
        customer.setAddress(updateRequest.address());
        customer.setGender(updateRequest.gender());
        customer.setPhoneNumber(updateRequest.phoneNumber());

        // Save updated customer
        repo.save(customer);
    }
    public void deleteCustomer(String token) {
        // Extract email from the token
        String email = jwtService.getEmailFromToken(token.replace("Bearer ", ""));
        // Fetch the customer from the database
        Customer customer = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Delete the customer from the database
        repo.delete(customer);
    }


}

