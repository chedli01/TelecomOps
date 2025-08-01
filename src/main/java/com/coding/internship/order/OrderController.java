package com.coding.internship.order;

import com.coding.internship.order.dto.OrderCreateDto;
import com.coding.internship.order.dto.OrderDataDto;
import com.coding.internship.order.dto.OrderUpdateDto;
import com.coding.internship.order.mapper.OrderMapper;
import com.coding.internship.order.model.Order;
import com.coding.internship.order.service.OrderService;
import com.coding.internship.user.client.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @PutMapping("/{id}")
    public OrderDataDto updateOrder(@RequestBody OrderUpdateDto orderUpdateDto,@PathVariable Long id){
        return orderMapper.mapToDto(orderService.updateOrder(id,orderUpdateDto));

    }
}
