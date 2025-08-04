package com.coding.internship.order.service;

import com.coding.internship.drools.dto.ResponseObjectDto;
import com.coding.internship.drools.service.DroolsService;
import com.coding.internship.invoice.dto.InvoiceCreateDto;
import com.coding.internship.invoice.enums.InvoiceStatus;
import com.coding.internship.invoice.service.InvoiceService;
import com.coding.internship.order.dto.OrderCreateDto;
import com.coding.internship.order.dto.OrderDataDto;
import com.coding.internship.order.dto.OrderItemCreateDto;
import com.coding.internship.order.dto.OrderUpdateDto;
import com.coding.internship.order.enums.OrderStatus;
import com.coding.internship.order.model.Order;
import com.coding.internship.order.model.OrderItem;
import com.coding.internship.order.repository.OrderRepository;
import com.coding.internship.subscription.dto.SubscriptionUpdateDto;
import com.coding.internship.subscription.model.Subscription;
import com.coding.internship.subscription.service.SubscriptionService;
import com.coding.internship.user.client.dto.UpdateClientDto;
import com.coding.internship.user.client.model.Client;
import com.coding.internship.user.client.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.mvel2.ast.Or;
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
    private final SubscriptionService subscriptionService;

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
        ResponseObjectDto responseObjectDto = droolsService.applyDiscountOnOrder(order);
        Order savedOrder =orderRepository.save(responseObjectDto.getOrder()) ;
        subscriptionService.updateSubscription(responseObjectDto.getSubscription().getId(), SubscriptionUpdateDto.builder().remainingData(responseObjectDto.getSubscription().getRemainingData()).build());
        clientService.updateClient(clientId, UpdateClientDto.builder().clientType(responseObjectDto.getClient().getType()).build());
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
    public Order updateOrder(Long id, OrderUpdateDto  orderUpdateDto){
        Order order = orderRepository.getReferenceById(id);
        if(orderUpdateDto.getOrderNumber() != null){
            order.setOrderNumber(orderUpdateDto.getOrderNumber());
        }
        if(orderUpdateDto.getDescription() != null){
            order.setDescription(orderUpdateDto.getDescription());
        }
        if(orderUpdateDto.getTotal() != null){
            order.setTotal(orderUpdateDto.getTotal());
        }
        if(orderUpdateDto.getDiscount() != null){
            order.setDiscount(orderUpdateDto.getDiscount());
        }
        if (orderUpdateDto.getStatus() != null){
            order.setStatus(orderUpdateDto.getStatus());
        }
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElseThrow(()->new RuntimeException("order not found"));
    }

    public Order passFreeOrder(OrderCreateDto orderCreateDto,Long clientId){
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .description(orderCreateDto.getDescription())
                .discount(0.0)
                .total(0.0)
                .orderItems(orderCreateDto.getOrderItems().stream().map(orderItemService::buildOrderItem).toList())
                .build();

        return orderRepository.save(order);


    }



}
