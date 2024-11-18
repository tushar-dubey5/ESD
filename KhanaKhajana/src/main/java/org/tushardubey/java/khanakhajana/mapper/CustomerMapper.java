package org.tushardubey.java.khanakhajana.mapper;

import org.springframework.stereotype.Service;
import org.tushardubey.java.khanakhajana.dto.CustomerRequest;
import org.tushardubey.java.khanakhajana.entity.Customer;

@Service
public class CustomerMapper {
    public Customer toEntity(CustomerRequest request) {
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password()) // Note: Password encoding handled in service layer
                .address(request.address())
                .gender(request.gender())
                .phoneNumber(request.phoneNumber())
                .build();
    }
}
