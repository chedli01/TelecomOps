package com.coding.internship.product.dto;

import com.coding.internship.product.enums.ProductCategory;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCriteriaDto {
    @Parameter(description = "product name",required = false)
    private String name;
    @Parameter(description = "product price",required = false)
    private Double price;
    @Parameter(description = "product min price",required = false)
    private Double minPrice;
    @Parameter(description = "product max price",required = false)
    private Double maxPrice;
    @Parameter(description = "product category",required = false)
    private ProductCategory category;
}
