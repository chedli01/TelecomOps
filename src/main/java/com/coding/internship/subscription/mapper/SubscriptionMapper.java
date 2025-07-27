package com.coding.internship.subscription.mapper;

import com.coding.internship.generic.GenericMapper;
import com.coding.internship.subscription.dto.SubscriptionDataDto;
import com.coding.internship.subscription.model.Subscription;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper extends GenericMapper<Subscription, SubscriptionDataDto> {
}
