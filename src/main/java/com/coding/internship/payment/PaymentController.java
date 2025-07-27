package com.coding.internship.payment;

import com.coding.internship.payment.dto.PaymentDataDto;
import com.coding.internship.payment.enums.PaymentMethod;
import com.coding.internship.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/invoice/{invoiceId}")
    public PaymentDataDto payInvoice(@PathVariable Long invoiceId, @RequestBody PaymentMethod paymentMethod){
        return paymentService.createPayment(invoiceId,paymentMethod);

    }
}
