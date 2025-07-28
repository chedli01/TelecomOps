package com.coding.internship.order.mapper;

import com.coding.internship.generic.GenericMapper;
import com.coding.internship.order.dto.OrderDataDto;
import com.coding.internship.order.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper extends GenericMapper<Order, OrderDataDto> {
    OrderDataDto mapToDto(Order order);
    Order mapToEntity(OrderDataDto orderDataDto);
}
