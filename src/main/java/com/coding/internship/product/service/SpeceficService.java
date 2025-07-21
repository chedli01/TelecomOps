package com.coding.internship.product.service;

import com.coding.internship.product.exception.RessourceNotFoundException;
import com.coding.internship.product.repository.ProductRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class SpeceficService {
    private final ProductRepository productRepository;


    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RessourceNotFoundException("product not found");
        }
        productRepository.deleteById(id);
    }
}
