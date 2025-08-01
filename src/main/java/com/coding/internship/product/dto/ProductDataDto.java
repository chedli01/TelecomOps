package com.coding.internship.product.dto;

import com.coding.internship.product.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDataDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private ProductCategory category;
}
