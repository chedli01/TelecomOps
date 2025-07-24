package com.coding.internship.subscription.model;

import com.coding.internship.plan.model.Plan;
import com.coding.internship.subscription.enums.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "subscriptions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double remainingData;
    private Double remainingCalls;
    private Integer remainingSms;
    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;
    private Double discount;
    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;
}
