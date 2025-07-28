package com.coding.internship.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemCreateDto {
    private Long productId;
    private Integer quantity;
    private Double unitPrice;

}
