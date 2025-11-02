package com.andrew.product_service.service;

import com.andrew.product_service.dto.ProductRequest;
import com.andrew.product_service.dto.ProductResponse;
import com.andrew.product_service.model.Product;
import com.andrew.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .prise(productRequest.getPrise())
                .build();

        productRepository.save(product);
        log.info("Product [{}] is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        log.info("Getting all Products");
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .prise(product.getPrise())
                .build();
    }
}
