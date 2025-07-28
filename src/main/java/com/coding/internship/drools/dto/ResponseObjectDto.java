package com.coding.internship.drools.dto;

import com.coding.internship.order.model.Order;
import com.coding.internship.subscription.model.Subscription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseObjectDto {
    private Order order;
    private Subscription subscription;
}
