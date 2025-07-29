package com.coding.internship.invoice.service;

import com.coding.internship.invoice.dto.InvoiceCreateDto;
import com.coding.internship.invoice.dto.InvoiceUpdateDto;
import com.coding.internship.invoice.enums.InvoiceStatus;
import com.coding.internship.invoice.model.Invoice;
import com.coding.internship.invoice.model.OrderInvoice;
import com.coding.internship.invoice.model.SubscriptionInvoice;
import com.coding.internship.invoice.repository.InvoiceRepository;
import com.coding.internship.invoice.repository.OrderInvoiceRepository;
import com.coding.internship.invoice.repository.SubscriptionInvoiceRepository;
import com.coding.internship.order.model.Order;
import com.coding.internship.subscription.model.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final SubscriptionInvoiceRepository subscriptionInvoiceRepository;
    private final OrderInvoiceRepository orderInvoiceRepository;

    public SubscriptionInvoice createSubInvoice(Subscription subscription, InvoiceCreateDto invoiceCreateDto){

        var invoice = SubscriptionInvoice.builder().invoiceNumber(invoiceCreateDto.getInvoiceNumber())
                .description(invoiceCreateDto.getDescription())
                .dueDate(invoiceCreateDto.getDueDate())
                .total(invoiceCreateDto.getTotal())
                .status(invoiceCreateDto.getStatus())
                .subscription(subscription)
                .build();
        return subscriptionInvoiceRepository.save(invoice);
    }
    public OrderInvoice createOrderInvoice(InvoiceCreateDto invoiceCreateDto, Order order){
        var invoice = OrderInvoice.builder().invoiceNumber(invoiceCreateDto.getInvoiceNumber())
                .description(invoiceCreateDto.getDescription())
                .dueDate(invoiceCreateDto.getDueDate())
                .total(invoiceCreateDto.getTotal())
                .status(invoiceCreateDto.getStatus())
                .order(order)
                .build();
        return orderInvoiceRepository.save(invoice);
    }
    public Invoice getInvoiceById(Long id){
        return invoiceRepository.findById(id).orElseThrow(()->new RuntimeException("invoice not found"));
    }
    public Invoice updateInvoice(Long id, InvoiceUpdateDto invoiceUpdateDto){
        Invoice invoice= getInvoiceById(id);
        if(invoiceUpdateDto.getDescription()!=null){
            invoice.setDescription(invoiceUpdateDto.getDescription());
        }
        if(invoiceUpdateDto.getDueDate()!=null){
            invoice.setDueDate(invoiceUpdateDto.getDueDate());
        }
        if(invoiceUpdateDto.getStatus()!=null){
            invoice.setStatus(invoiceUpdateDto.getStatus());
        }
        if(invoiceUpdateDto.getTotal()!=null){
            invoice.setTotal(invoiceUpdateDto.getTotal());
        }
        return invoiceRepository.save(invoice);
    }

}
