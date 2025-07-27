package com.coding.internship.subscription.dto;

import com.coding.internship.subscription.enums.SubscriptionStatus;
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
    private Double remainingData;
    private Double remainingCalls;
    private Integer remainingSms;
    private Double discount;
    private SubscriptionStatus status;
}

