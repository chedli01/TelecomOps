package com.coding.internship.product;

import com.coding.internship.product.dto.ProductCreationDto;
import com.coding.internship.product.dto.ProductCriteriaDto;
import com.coding.internship.product.dto.ProductDataDto;
import com.coding.internship.product.dto.ProductUpdateDto;
import com.coding.internship.exception.RessourceNotFoundException;
import com.coding.internship.product.mapper.ProductMapper;
import com.coding.internship.product.model.Product;
import com.coding.internship.product.service.ProductService;
import com.coding.internship.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;


    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductCreationDto productCreationDto) {
        Product product = productService.createProduct(productCreationDto);
        return new ResponseEntity<>(product,HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<ProductDataDto> getProductById(@PathVariable Long id, @AuthenticationPrincipal User user) {
        System.out.println("user : " + user.getEmail()+user.getFirstName()+user.getId());
        return ResponseEntity.ok(productMapper.mapToDto(productService.getProductById(id)));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDataDto> updateProduct(@RequestBody ProductUpdateDto productUpdateDto, @PathVariable Long id) {
        return ResponseEntity.ok(productMapper.mapToDto(productService.updateProduct(productUpdateDto, id)));

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
        try {
            productService.deleteProductById(id);
        } catch (Exception e) {
            throw new RessourceNotFoundException("product not found");
        }
        return ResponseEntity.ok("product with id " + id + " deleted");
    }

    @GetMapping("/find-by-price-between")
    public ResponseEntity<List<ProductDataDto>> findProductByPriceBetween(@RequestParam Double min, @RequestParam Double max) {
        return ResponseEntity.ok(productService.findProductByPriceBetween(min, max).stream().map(productMapper::mapToDto).toList() );
    }

    @GetMapping
    public ResponseEntity<List<ProductDataDto>> findByCriteria(@ParameterObject ProductCriteriaDto criteriaDto,@ParameterObject Pageable pageable)
    {
        return ResponseEntity.ok(productService.findByCriteria(criteriaDto,pageable).stream().map(productMapper::mapToDto).toList() );
    }

    @GetMapping("/gifted")
    public ProductDataDto getGiftedProducts(){
        return productMapper.mapToDto(productService.findGiftedProduct());
    }



}
