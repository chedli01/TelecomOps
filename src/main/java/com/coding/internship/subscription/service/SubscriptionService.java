package com.coding.internship.subscription.service;

import com.coding.internship.plan.model.Plan;
import com.coding.internship.plan.repository.PlanRepository;
import com.coding.internship.subscription.dto.SubscriptionDataDto;
import com.coding.internship.subscription.enums.SubscriptionStatus;
import com.coding.internship.subscription.mapper.SubscriptionMapper;
import com.coding.internship.subscription.model.Subscription;
import com.coding.internship.subscription.repository.SubscriptionRepository;
import com.coding.internship.user.client.model.Client;
import com.coding.internship.user.client.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final PlanRepository planRepository;
    private final ClientRepository clientRepository;
    private final SubscriptionMapper subscriptionMapper;

    public SubscriptionDataDto subscribeToPlan(Long planId, Long clientId){
        Plan plan = planRepository.findById(planId).orElseThrow();
        Client client = clientRepository.findById(clientId).orElseThrow();
        var subscription = Subscription.builder()
                           .plan(plan)
                           .client(client).startDate(LocalDateTime.now()).endDate(LocalDateTime.now().plusDays(plan.getValidityDays()))
                .remainingData(plan.getDataQuota()).remainingCalls(plan.getCallsMinutes()).remainingSms(plan.getSmsNumber()).discount(0.0).status(SubscriptionStatus.ACTIVE)
                .build();
        Subscription savedSub = subscriptionRepository.save(subscription);
        return subscriptionMapper.mapToDto(savedSub);



    }
    public List<SubscriptionDataDto> getAllSubscriptions(){
        return subscriptionRepository.findAll().stream().map(subscriptionMapper::mapToDto).toList();
    }
    public SubscriptionDataDto makeCall(Long clientId,Double minutes){
        Subscription subscription = subscriptionRepository.findByClientId(clientId);
        if(subscription.getRemainingCalls()<minutes){
            throw new IllegalArgumentException("not enough calls");
        }
        if(SubscriptionStatus.INACTIVE.equals(subscription.getStatus())){
            throw new IllegalArgumentException("subscription is inactive");
        }
        subscription.setRemainingCalls(subscription.getRemainingCalls()-minutes);
        subscriptionRepository.save(subscription);
        return subscriptionMapper.mapToDto(subscription);

    }


}
