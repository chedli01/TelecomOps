package com.coding.internship.product.dto;

import com.coding.internship.product.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDto {
    private String name;

    private String description;

    private Double price;

    private ProductCategory category;
}
