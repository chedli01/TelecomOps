package com.coding.internship.invoice.dto;

import com.coding.internship.invoice.enums.InvoiceStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceUpdateDto {
    private String description;
    private Double total;
    private LocalDateTime dueDate;
    private InvoiceStatus status;
}
