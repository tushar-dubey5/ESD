package org.tushardubey.resturant.mapper;
import org.tushardubey.resturant.entity.Customer;
import org.tushardubey.resturant.dto.CustomerRequest;
import org.springframework.stereotype.Service;
@Service
public class CustomerMapper {
    public Customer toEntity(CustomerRequest request)
    {
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .build();
    }
}

