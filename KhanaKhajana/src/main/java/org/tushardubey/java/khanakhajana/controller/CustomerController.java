package org.tushardubey.java.khanakhajana.controller;

import org.springframework.web.bind.annotation.*;
import org.tushardubey.java.khanakhajana.dto.CustomerRequest;
import org.tushardubey.java.khanakhajana.service.CustomerService;
import org.tushardubey.java.khanakhajana.dto.LoginRequest;
import org.tushardubey.java.khanakhajana.dto.UpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerservice;
    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request){
        return ResponseEntity.ok(customerservice.createCustomer(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginCustomer(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(customerservice.loginCustomer(request));
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateCustomer(
            @RequestHeader("Authorization") String token,
            @RequestBody @Valid UpdateRequest updateRequest) {
        customerservice.updateCustomer(token, updateRequest);
        return ResponseEntity.ok("Customer information updated successfully");
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCustomer(@RequestHeader("Authorization") String token) {
        customerservice.deleteCustomer(token);
        return ResponseEntity.ok("Customer account deleted successfully");
    }
}
