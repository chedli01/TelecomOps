package com.coding.internship.invoice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "order_invoices")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class OrderInvoice extends Invoice{

}
