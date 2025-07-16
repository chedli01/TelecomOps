package com.coding.internship.product_service.dto;

import com.coding.internship.product_service.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreationDto {
    private String name;
    private String description;
    private Double price;
    private ProductCategory category;
}
