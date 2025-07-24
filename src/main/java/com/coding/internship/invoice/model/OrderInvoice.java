package com.coding.internship.invoice.model;

import com.coding.internship.order.model.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "order_invoices")
@Data
@NoArgsConstructor
@SuperBuilder
public class OrderInvoice extends Invoice{
    @OneToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;

}
