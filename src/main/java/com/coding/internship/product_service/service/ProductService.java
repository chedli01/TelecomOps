package com.coding.internship.product_service.service;

import com.coding.internship.product_service.dto.ProductCreationDto;
import com.coding.internship.product_service.dto.ProductCriteriaDto;
import com.coding.internship.product_service.dto.ProductDataDto;
import com.coding.internship.product_service.dto.ProductUpdateDto;
import com.coding.internship.product_service.enums.ProductCategory;
import com.coding.internship.product_service.exception.RessourceNotFoundException;
import com.coding.internship.product_service.mapper.ProductMapper;
import com.coding.internship.product_service.model.Product;
import com.coding.internship.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<ProductDataDto> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::mapToDto).toList();
    }

    public ProductDataDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("product not found"));
        return productMapper.mapToDto(product);
    }

    public ProductDataDto updateProduct(ProductUpdateDto productUpdateDto, Long id) {
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
        return productMapper.mapToDto(productRepository.save(product));
    }

    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RessourceNotFoundException("product not found");
        }
        productRepository.deleteById(id);
    }

    public List<ProductDataDto> findProductByPriceBetween(Double min, Double max) {
//        return productRepository.findProductByPriceBetween(min, max).stream().map(productMapper::mapToDto).toList();
        Specification<Product> spec = ProductSpecifications.hasPriceBetween(min,max);
        return productRepository.findAll(spec).stream().map(productMapper::mapToDto).toList();

    }

    public List<ProductDataDto> findByCriteria(ProductCriteriaDto criteriaDto,Pageable pageable) {
        Specification<Product> spec = ProductSpecifications.hasCategory(criteriaDto.getCategory()).and(ProductSpecifications.hasPriceBetween(criteriaDto.getMinPrice(), criteriaDto.getMaxPrice())).and(ProductSpecifications.hasName(criteriaDto.getName())).and(ProductSpecifications.hasPrice(criteriaDto.getPrice()));
        return productRepository.findAll(spec,pageable).stream().map(productMapper::mapToDto).toList();
    }
    public List<ProductDataDto> findPaginated(Pageable pageable){
        Page<Product> page = productRepository.findAll(pageable);
        return page.getContent().stream().map(productMapper::mapToDto).toList();
    }

    public Product makeDiscount(Long id) {
        Optional<Product> product=productRepository.findById(id);
        if(product.isEmpty()){
            throw new RessourceNotFoundException("product not found");
        }

        return productRepository.save(droolsService.applyDiscount(product.get()));

    }
    public List<Product> makeDiscountForCategory(String category){
        List<Product> products=productRepository.findAll();
        return droolsService.applyDiscountForCategory(products,category);
    }


}
