package org.tushardubey.java.khanakhajana.mapper;


import org.tushardubey.java.khanakhajana.dto.ProductRequest;
import org.tushardubey.java.khanakhajana.dto.ProductResponse;
import org.tushardubey.java.khanakhajana.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .build();
    }

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getPrice());
    }
}
