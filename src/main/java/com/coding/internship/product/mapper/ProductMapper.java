package com.coding.internship.product.mapper;

import com.coding.internship.product.dto.ProductDataDto;
import com.coding.internship.product.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDataDto mapToDto(Product product);
    Product mapToEntity(ProductDataDto productDataDto);


}
