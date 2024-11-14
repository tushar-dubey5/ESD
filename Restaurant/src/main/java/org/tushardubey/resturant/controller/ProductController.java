package org.tushardubey.resturant.controller;

import org.tushardubey.resturant.dto.ProductRequest;
import org.tushardubey.resturant.dto.ProductResponse;
import org.tushardubey.resturant.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;
    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody @Valid ProductRequest request){
        return ResponseEntity.ok(productService.createProduct(request));
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping("/{id}")
    public  ResponseEntity<ProductResponse> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @GetMapping("/top")
    public ResponseEntity<List<ProductResponse>> getTopProductsByPriceRange() {
        return ResponseEntity.ok(productService.getTopProductsByPriceRange());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(productService.updateProduct(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
}
