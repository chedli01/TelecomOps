package com.coding.internship.product_service.mapper;

import com.coding.internship.product_service.dto.ProductDataDto;
import com.coding.internship.product_service.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDataDto mapToDto(Product product);
    Product mapToEntity(ProductDataDto productDataDto);


}
