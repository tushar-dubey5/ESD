package org.tushardubey.java.khanakhajana.service;

import org.tushardubey.java.khanakhajana.dto.ProductRequest;
import org.tushardubey.java.khanakhajana.dto.ProductResponse;
import org.tushardubey.java.khanakhajana.entity.Product;
import org.tushardubey.java.khanakhajana.mapper.ProductMapper;
import org.tushardubey.java.khanakhajana.repo.ProductRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    public String createProduct(ProductRequest request) {
        Product product = productMapper.toEntity(request);
        productRepo.save(product);
        return "Product Created";
    }

    public ProductResponse getProductById(Long id) {
        return productMapper.toResponse(productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found")));
    }

    public List<ProductResponse> getTopProductsByPriceRange() {
        return productRepo.findTop2ByPriceBetweenOrderByPrice(15, 30)
                .stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }
    public List<ProductResponse> getAllProducts() {
        return productRepo.findAll().stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    public String updateProduct(Long id, ProductRequest request) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        productRepo.save(product);
        return "Product Updated";
    }

    public String deleteProduct(Long id) {
        productRepo.deleteById(id);
        return "Product Deleted";
    }
}
