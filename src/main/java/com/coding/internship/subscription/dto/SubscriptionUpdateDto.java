package com.coding.internship.subscription.dto;

import com.coding.internship.subscription.enums.SubscriptionStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionUpdateDto {
    private Double remainingData;
    private Double remainingCalls;
    private Integer remainingSms;
    private SubscriptionStatus status;
    private Double discount;
}
