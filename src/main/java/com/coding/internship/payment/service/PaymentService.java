package com.coding.internship.payment.service;

import com.coding.internship.invoice.service.InvoiceService;
import com.coding.internship.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final InvoiceService invoiceService;

}
