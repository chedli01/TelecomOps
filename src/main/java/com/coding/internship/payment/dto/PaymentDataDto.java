package com.coding.internship.payment.dto;

import com.coding.internship.payment.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDataDto {
    private Long id;
    private String paymentNumber;
    private LocalDateTime paymentDate;
    private PaymentMethod paymentMethod;
}
