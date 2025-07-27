package com.coding.internship.payment.service;

import com.coding.internship.invoice.model.Invoice;
import com.coding.internship.invoice.service.InvoiceService;
import com.coding.internship.payment.dto.PaymentDataDto;
import com.coding.internship.payment.enums.PaymentMethod;
import com.coding.internship.payment.mapper.PaymentMapper;
import com.coding.internship.payment.model.Payment;
import com.coding.internship.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final InvoiceService invoiceService;
    private final PaymentMapper paymentMapper;

    public PaymentDataDto createPayment(Long invoiceId, PaymentMethod paymentMethod){
        Invoice invoice = invoiceService.getInvoiceById(invoiceId);
        UUID uuid = UUID.randomUUID();
        var payment = Payment.builder().paymentDate(LocalDateTime.now()).paymentNumber(uuid.toString()).paymentMethod(paymentMethod).invoice(invoice).build();
        Payment savedPayment = paymentRepository.save(payment);
        invoiceService.setPaid(invoiceId);
        return paymentMapper.mapToDto(savedPayment);
    }

}
