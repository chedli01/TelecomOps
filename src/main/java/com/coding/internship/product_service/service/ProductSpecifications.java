package com.coding.internship.product_service.service;

import com.coding.internship.product_service.enums.ProductCategory;
import com.coding.internship.product_service.model.Product;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class ProductSpecifications {

    public static Specification<Product> hasName(String name){
        return (root, query, cb) -> name==null?null : cb.like(root.get("name"), "%"+name+"%");
    }
    public static Specification<Product> hasPriceBetween(Double min, Double max){
        return (root, query, cb) -> min==null && max==null?null : cb.between(root.get("price"), min, max);
    }
    public static Specification<Product> hasCategory(ProductCategory category){
        return (root, query, cb) -> category==null?null : cb.equal(root.get("category"), category);
    }
    public static Specification<Product> hasPrice(Double price){
        return (root, query, cb) -> price==null?null : cb.equal(root.get("price"), price);
    }

}
