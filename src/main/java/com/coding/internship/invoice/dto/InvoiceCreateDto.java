package com.coding.internship.invoice.dto;

import com.coding.internship.invoice.enums.InvoiceStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceCreateDto {
    private String invoiceNumber;
    private String description;
    private LocalDateTime dueDate;
    private InvoiceStatus status;
    private Double total;
}
