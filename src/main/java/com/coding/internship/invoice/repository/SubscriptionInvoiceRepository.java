package com.coding.internship.invoice.repository;

import com.coding.internship.invoice.model.SubscriptionInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionInvoiceRepository extends JpaRepository<SubscriptionInvoice, Long> {
}
