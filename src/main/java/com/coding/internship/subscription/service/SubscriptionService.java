package com.coding.internship.subscription.service;

import com.coding.internship.subscription.dto.SubscriptionDataDto;
import com.coding.internship.subscription.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionDataDto subscribeToPlan(Long planId, Long clientId){
        return null;

    }

}
