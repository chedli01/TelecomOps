package com.coding.internship.invoice.service;

import com.coding.internship.invoice.dto.InvoiceCreateDto;
import com.coding.internship.invoice.model.SubscriptionInvoice;
import com.coding.internship.invoice.repository.InvoiceRepository;
import com.coding.internship.invoice.repository.OrderInvoiceRepository;
import com.coding.internship.invoice.repository.SubscriptionInvoiceRepository;
import com.coding.internship.subscription.model.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final SubscriptionInvoiceRepository subscriptionInvoiceRepository;
    private final OrderInvoiceRepository orderInvoiceRepository;

    public String createSubInvoice(Subscription subscription, InvoiceCreateDto invoiceCreateDto){
        var invoice = SubscriptionInvoice.builder().invoiceNumber(invoiceCreateDto.getInvoiceNumber())
                .description(invoiceCreateDto.getDescription())
                .dueDate(invoiceCreateDto.getDueDate())
                .total(invoiceCreateDto.getTotal())
                .status(invoiceCreateDto.getStatus())
                .subscription(subscription)
                .build();
        subscriptionInvoiceRepository.save(invoice);
        return invoice.getInvoiceNumber();


    }
}
