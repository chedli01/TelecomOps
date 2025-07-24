package com.coding.internship.invoice.repository;

import com.coding.internship.invoice.model.OrderInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderInvoiceRepository extends JpaRepository<OrderInvoice, Long> {
}
