package com.coding.internship.order;

import com.coding.internship.order.dto.OrderCreateDto;
import com.coding.internship.order.dto.OrderDataDto;
import com.coding.internship.order.mapper.OrderMapper;
import com.coding.internship.order.model.Order;
import com.coding.internship.order.service.OrderService;
import com.coding.internship.user.client.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public OrderDataDto makeOrder(@RequestBody OrderCreateDto orderCreateDto, @AuthenticationPrincipal Client client){
        return orderMapper.mapToDto(orderService.makeOrder(orderCreateDto,client.getId())) ;
    }
}
