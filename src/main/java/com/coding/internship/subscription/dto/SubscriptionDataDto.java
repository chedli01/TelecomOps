package com.coding.internship.subscription.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDataDto {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String planName;
    private String planDescription;
    private Double planPrice;
    private Double totalToPay;

}
