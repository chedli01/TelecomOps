package com.coding.internship.product.service;

import com.coding.internship.drools.service.DroolsService;
import com.coding.internship.product.dto.ProductCreationDto;
import com.coding.internship.product.dto.ProductCriteriaDto;
import com.coding.internship.product.dto.ProductDataDto;
import com.coding.internship.product.dto.ProductUpdateDto;
import com.coding.internship.exception.RessourceNotFoundException;
import com.coding.internship.product.enums.ProductCategory;
import com.coding.internship.product.mapper.ProductMapper;
import com.coding.internship.product.model.Product;
import com.coding.internship.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final DroolsService droolsService;


    public Product createProduct(ProductCreationDto productCreationDto) {
        var product = Product
                .builder()
                .name(productCreationDto.getName())
                .description(productCreationDto.getDescription())
                .price(productCreationDto.getPrice())
                .category(productCreationDto.getCategory())
                .build();
        return productRepository.save(product);


    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("product not found"));
    }

    public Product updateProduct(ProductUpdateDto productUpdateDto, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("product not found"));
        if (productUpdateDto.getName() != null) {
            product.setName(productUpdateDto.getName());

        }
        if (productUpdateDto.getDescription() != null) {
            product.setDescription(productUpdateDto.getDescription());
        }
        if (productUpdateDto.getPrice() != null) {
            product.setPrice(productUpdateDto.getPrice());
        }
        if (productUpdateDto.getCategory() != null) {
            product.setCategory(productUpdateDto.getCategory());
        }
        return productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RessourceNotFoundException("product not found");
        }
        productRepository.deleteById(id);
    }

    public List<Product> findProductByPriceBetween(Double min, Double max) {
//        return productRepository.findProductByPriceBetween(min, max).stream().map(productMapper::mapToDto).toList();
        Specification<Product> spec = ProductSpecifications.hasPriceBetween(min,max);
        return productRepository.findAll(spec);

    }

    public List<Product> findByCriteria(ProductCriteriaDto criteriaDto,Pageable pageable) {
        Specification<Product> spec = ProductSpecifications.hasCategory(criteriaDto.getCategory()).and(ProductSpecifications.hasPriceBetween(criteriaDto.getMinPrice(), criteriaDto.getMaxPrice())).and(ProductSpecifications.hasName(criteriaDto.getName())).and(ProductSpecifications.hasPrice(criteriaDto.getPrice()));
        return productRepository.findAll(spec,pageable).stream().toList();
    }
    public List<ProductDataDto> findPaginated(Pageable pageable){
        Page<Product> page = productRepository.findAll(pageable);
        return page.getContent().stream().map(productMapper::mapToDto).toList();
    }




    public Product findGiftedProduct(){
        List<Product> products= findByCriteria(ProductCriteriaDto.builder().category(ProductCategory.ACCESSORY).build(),Pageable.unpaged());
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("gifted product list is null or empty");
        }
        Random random = new Random();
        return products.get(random.nextInt(products.size()));



    }


}
