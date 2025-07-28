package com.coding.internship.order.service;

import com.coding.internship.order.dto.OrderItemCreateDto;
import com.coding.internship.order.model.OrderItem;
import com.coding.internship.order.repository.OrderItemRepository;
import com.coding.internship.product.model.Product;
import com.coding.internship.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;

    public OrderItem buildOrderItem(OrderItemCreateDto orderItemCreateDto){
        Product product = productService.getProductById(orderItemCreateDto.getProductId());
        return OrderItem.builder().product(product).quantity(orderItemCreateDto.getQuantity()).unitPrice(product.getPrice()).build();

    }


}
