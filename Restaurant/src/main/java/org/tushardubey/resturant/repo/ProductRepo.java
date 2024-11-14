package org.tushardubey.resturant.repo;

import org.tushardubey.resturant.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice ORDER BY p.price")
    List<Product> findTop2ByPriceBetweenOrderByPrice(double minPrice, double maxPrice);
}
