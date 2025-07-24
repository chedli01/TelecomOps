package com.coding.internship.invoice.model;

import com.coding.internship.subscription.model.Subscription;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "subscription_invoices")
@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor

public class SubscriptionInvoice extends Invoice{
    @OneToOne
    @JoinColumn(name = "subscription_id",nullable = false)
    private Subscription subscription;
}
