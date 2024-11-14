package org.tushardubey.resturant.mapper;

import org.tushardubey.resturant.dto.ProductRequest;
import org.tushardubey.resturant.dto.ProductResponse;
import org.tushardubey.resturant.entity.Product;
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
