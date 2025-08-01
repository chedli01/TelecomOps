package com.coding.internship.order.dto;

import com.coding.internship.order.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderUpdateDto {
    private String orderNumber;
    private String description;
    private Double total;
    private Double discount;
    private OrderStatus status;
}
