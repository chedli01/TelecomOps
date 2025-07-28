package com.coding.internship.order.service;

import com.coding.internship.drools.service.DroolsService;
import com.coding.internship.invoice.dto.InvoiceCreateDto;
import com.coding.internship.invoice.enums.InvoiceStatus;
import com.coding.internship.invoice.service.InvoiceService;
import com.coding.internship.order.dto.OrderCreateDto;
import com.coding.internship.order.dto.OrderItemCreateDto;
import com.coding.internship.order.enums.OrderStatus;
import com.coding.internship.order.model.Order;
import com.coding.internship.order.model.OrderItem;
import com.coding.internship.order.repository.OrderRepository;
import com.coding.internship.user.client.model.Client;
import com.coding.internship.user.client.service.ClientService;
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
    private final ClientService clientService;
    private final InvoiceService invoiceService;
    private final DroolsService droolsService;

    public Order makeOrder(OrderCreateDto orderCreateDto,Long clientId){
        List<OrderItem> orderItems = new ArrayList<>();
        Client client = clientService.findClientById(clientId);
        for(OrderItemCreateDto orderItemCreateDto:orderCreateDto.getOrderItems()){
            orderItems.add(orderItemService.buildOrderItem(orderItemCreateDto));
        }
        UUID uuid = UUID.randomUUID();
        Order order = Order.builder().orderNumber(uuid.toString())
                .description(orderCreateDto.getDescription())
                .total(calculateTotal(orderItems))
                .discount(0.0)
                .status(OrderStatus.PENDING)
                .orderItems(orderItems)
                .client(client)
                .build();
        for (OrderItem orderItem : orderItems){
            orderItem.setOrder(order);
        }
//        Order savedOrder = orderRepository.save(order);
        Order savedOrder =orderRepository.save(droolsService.applyDiscountForStudent(order)) ;
        invoiceService.createOrderInvoice(InvoiceCreateDto.builder().invoiceNumber(uuid.toString()).description(order.getDescription()).dueDate(savedOrder.getCreatedAt().plusDays(7L)).status(InvoiceStatus.UNPAID).total(order.getTotal()-savedOrder.getDiscount()).build(), savedOrder);
        return savedOrder;
    }
    private Double calculateTotal(List<OrderItem> orderItems){
        Double total = 0.0;
        for(OrderItem orderItem:orderItems){
            total+=orderItem.getQuantity()*orderItem.getUnitPrice();

        }
        return total;

    }


}
