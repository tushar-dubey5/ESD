package org.tushardubey.resturant.repo;
import org.tushardubey.resturant.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepo  extends  JpaRepository<Customer, Long> {
}
