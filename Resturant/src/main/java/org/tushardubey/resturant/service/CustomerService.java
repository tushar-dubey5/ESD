package org.tushardubey.resturant.service;

import org.tushardubey.resturant.dto.CustomerRequest;
import org.tushardubey.resturant.dto.CustomerResponse;
import org.tushardubey.resturant.entity.Customer;
import org.tushardubey.resturant.mapper.CustomerMapper;
import org.tushardubey.resturant.repo.CustomerRepo;
import org.tushardubey.resturant.dto.LoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor

public class CustomerService {
    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    public String createCustomer(CustomerRequest request){
        Customer customer=mapper.toEntity(request);
        repo.save(customer);
        return "Created";
    }

    public String loginCustomer(LoginRequest request) {
        Customer customer = repo.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (customer.getPassword().equals(request.password())) {
            return "Login successful";
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}

