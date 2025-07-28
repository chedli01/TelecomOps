package com.coding.internship.order.service;

import com.coding.internship.order.dto.OrderCreateDto;
import com.coding.internship.order.dto.OrderItemCreateDto;
import com.coding.internship.order.enums.OrderStatus;
import com.coding.internship.order.model.Order;
import com.coding.internship.order.model.OrderItem;
import com.coding.internship.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;

    public Order makeOrder(OrderCreateDto orderCreateDto){
        List<OrderItem> orderItems = new ArrayList<>();
        for(OrderItemCreateDto orderItemCreateDto:orderCreateDto.getOrderItems()){
            orderItems.add(orderItemService.buildOrderItem(orderItemCreateDto));
        }
        UUID uuid = UUID.randomUUID();
        Order order = Order.builder().orderNumber(uuid.toString())
                .description(orderCreateDto.getDescription())
                .total(calculateTotal(orderItems))
                .discount(0.0)
                .status(OrderStatus.PENDING).orderItems(orderItems).build();
        for (OrderItem orderItem : orderItems){
            orderItem.setOrder(order);
        }
        Order savedOrder = orderRepository.save(order);
        return savedOrder;
    }
    public Double calculateTotal(List<OrderItem> orderItems){
        Double total = 0.0;
        for(OrderItem orderItem:orderItems){
            total+=orderItem.getQuantity()*orderItem.getUnitPrice();

        }
        return total;

    }


}
