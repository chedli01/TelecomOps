package com.coding.internship.order;

import com.coding.internship.order.dto.OrderCreateDto;
import com.coding.internship.order.model.Order;
import com.coding.internship.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public Order makeOrder(@RequestBody OrderCreateDto orderCreateDto){
        return orderService.makeOrder(orderCreateDto);
    }
}
