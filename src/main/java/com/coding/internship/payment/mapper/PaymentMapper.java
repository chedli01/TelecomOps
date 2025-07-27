package com.coding.internship.payment.mapper;

import com.coding.internship.generic.GenericMapper;
import com.coding.internship.payment.dto.PaymentDataDto;
import com.coding.internship.payment.model.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper extends GenericMapper<Payment, PaymentDataDto> {
}
