package com.coding.internship.subscription.model;

import com.coding.internship.invoice.model.Invoice;
import com.coding.internship.invoice.model.SubscriptionInvoice;
import com.coding.internship.plan.model.Plan;
import com.coding.internship.subscription.enums.SubscriptionStatus;
import com.coding.internship.user.client.model.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "subscriptions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
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
    @Builder.Default
    private SubscriptionStatus status = SubscriptionStatus.ACTIVE;
    private Double discount;
    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;
    @ManyToOne
    @JoinColumn(name = "client_id",nullable = false)
    private Client client;
    @OneToOne(mappedBy = "subscription")
    private SubscriptionInvoice invoice;
}
