package com.coding.internship.product;

import com.coding.internship.product.dto.ProductCreationDto;
import com.coding.internship.product.dto.ProductCriteriaDto;
import com.coding.internship.product.dto.ProductDataDto;
import com.coding.internship.product.dto.ProductUpdateDto;
import com.coding.internship.exception.RessourceNotFoundException;
import com.coding.internship.product.model.Product;
import com.coding.internship.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductCreationDto productCreationDto) {
        Product product = productService.createProduct(productCreationDto);
        return new ResponseEntity<>(product,HttpStatus.CREATED);
    }


    @GetMapping("{id}")
    public ResponseEntity<ProductDataDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDataDto> updateProduct(@RequestBody ProductUpdateDto productUpdateDto, @PathVariable Long id) {
        return ResponseEntity.ok(productService.updateProduct(productUpdateDto, id));

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
        return ResponseEntity.ok(productService.findProductByPriceBetween(min, max));
    }

    @GetMapping
    public ResponseEntity<List<ProductDataDto>> findByCriteria(@ParameterObject ProductCriteriaDto criteriaDto,@ParameterObject Pageable pageable)
    {
        return ResponseEntity.ok(productService.findByCriteria(criteriaDto,pageable));
    }
    @PostMapping("/discount/{id}")
    public Product applyDiscount(@PathVariable Long id){
        return productService.makeDiscount(id);
    }
//    @PostMapping("/discount/category/{category}")
//    public List<Product> applyDiscountForCategory(@PathVariable String category){
//        return productService.makeDiscountForCategory(category);
//    }
//    @PostMapping("/change/{id}")
//    public Product changeInDb(@PathVariable Long id){
//        return productService.changeInDb(id);
//    }



}
