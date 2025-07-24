package com.coding.internship.order.model;

import com.coding.internship.invoice.model.OrderInvoice;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    private String description;
    private Double total;
    private Double discount;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
    @OneToOne(mappedBy = "order")
    private OrderInvoice invoice;
}
