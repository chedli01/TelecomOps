package com.coding.internship.subscription.service;

import com.coding.internship.drools.dto.CallVerificationRequest;
import com.coding.internship.drools.dto.DataVerificationRequest;
import com.coding.internship.drools.dto.SmsVerificationRequest;
import com.coding.internship.drools.service.DroolsService;
import com.coding.internship.invoice.dto.InvoiceCreateDto;
import com.coding.internship.invoice.dto.InvoiceUpdateDto;
import com.coding.internship.invoice.enums.InvoiceStatus;
import com.coding.internship.invoice.service.InvoiceService;
import com.coding.internship.plan.model.Plan;
import com.coding.internship.plan.service.PlanService;
import com.coding.internship.subscription.dto.SubscriptionDataDto;
import com.coding.internship.subscription.dto.SubscriptionUpdateDto;
import com.coding.internship.subscription.enums.SubscriptionStatus;
import com.coding.internship.subscription.mapper.SubscriptionMapper;
import com.coding.internship.subscription.model.Subscription;
import com.coding.internship.subscription.repository.SubscriptionRepository;
import com.coding.internship.user.client.model.Client;
import com.coding.internship.user.client.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final PlanService planService;
    private final ClientService clientService;
    private final InvoiceService invoiceService;
    private final DroolsService droolsService;

    public Subscription subscribeToPlan(Long planId, Long clientId){
        Plan plan = planService.getPlanById(planId);
        Client client = clientService.findClientById(clientId);
        var subscription = Subscription.builder()
                           .plan(plan)
                           .client(client).startDate(LocalDateTime.now()).endDate(LocalDateTime.now().plusDays(plan.getValidityDays()))
                .remainingData(plan.getDataQuota()).remainingCalls(plan.getCallsMinutes()).remainingSms(plan.getSmsNumber()).discount(0.0).status(SubscriptionStatus.ACTIVE)
                .build();
        Subscription savedSub = subscriptionRepository.save(droolsService.applyDiscountOnSub(subscription));
        UUID uuid = UUID.randomUUID();


        invoiceService.createSubInvoice(savedSub, InvoiceCreateDto.builder().invoiceNumber(uuid.toString()).description(plan.getDescription()).dueDate(savedSub.getEndDate().minusDays(3L)).status(InvoiceStatus.UNPAID).total(plan.getPrice()-savedSub.getDiscount()).build());
        return savedSub;



    }

    public List<Subscription> getAllSubscriptions(){
        return subscriptionRepository.findAll();
    }

    public Subscription makeCall(Long clientId,Double minutes){
        Subscription subscription = clientService.getActiveSub(clientId);
        CallVerificationRequest callVerificationRequest = CallVerificationRequest.builder().minutesConsumed(minutes).build();
        if(droolsService.verifyCalls(subscription,callVerificationRequest)==false){
            throw new IllegalArgumentException("not valid call transaction");
        }
        return updateSubscription(subscription.getId(),SubscriptionUpdateDto.builder().remainingCalls(subscription.getRemainingCalls()-minutes).build());
    }

    public Subscription makeSms(Long clientId){
        Subscription subscription =clientService.getActiveSub(clientId);
        SmsVerificationRequest smsVerificationRequest = SmsVerificationRequest.builder().smsConsumed(1).build();
        if(droolsService.verifySms(subscription,smsVerificationRequest)==false){
            throw new IllegalArgumentException("not valid sms  transaction");
        }
        return updateSubscription(subscription.getId(),SubscriptionUpdateDto.builder().remainingSms(subscription.getRemainingSms()-1).build());
    }

    public Subscription consumeData(Long clientId,Double data){
        Subscription subscription = clientService.getActiveSub(clientId);
        DataVerificationRequest dataVerificationRequest = DataVerificationRequest.builder().consumedData(data).build();


        if(droolsService.verifyData(subscription,dataVerificationRequest)==false){
            throw new IllegalArgumentException("not valid transaction");
        }

        return updateSubscription(subscription.getId(),SubscriptionUpdateDto.builder().remainingData(subscription.getRemainingData()-data).build());
    }

    public Subscription updateSubscription(Long id, SubscriptionUpdateDto subscriptionUpdateDto){
        Subscription subscription = subscriptionRepository.findById(id).orElseThrow(()->new RuntimeException("subscription not found"));
        if(subscriptionUpdateDto.getDiscount()!=null){
            subscription.setDiscount(subscriptionUpdateDto.getDiscount());
        }
        if(subscriptionUpdateDto.getRemainingCalls()!=null){
            subscription.setRemainingCalls(subscriptionUpdateDto.getRemainingCalls());
        }
        if(subscriptionUpdateDto.getRemainingData()!=null){
            subscription.setRemainingData(subscriptionUpdateDto.getRemainingData());
        }
        if(subscriptionUpdateDto.getRemainingSms()!=null){
            subscription.setRemainingSms(subscriptionUpdateDto.getRemainingSms());
        }
        if(subscriptionUpdateDto.getStatus()!=null){
            subscription.setStatus(subscriptionUpdateDto.getStatus());
        }
        return subscriptionRepository.save(subscription);


    }
    public Subscription cancelSub(Long clientId){
        Subscription subscription = clientService.getActiveSub(clientId);
        if(SubscriptionStatus.INACTIVE.equals(subscription.getStatus())){
            throw new IllegalArgumentException("subscription is already inactive");
        }
        invoiceService.updateInvoice(subscription.getInvoice().getId(), InvoiceUpdateDto.builder().status(InvoiceStatus.CANCELLED).build());
        return updateSubscription(subscription.getId(),SubscriptionUpdateDto.builder().status(SubscriptionStatus.INACTIVE).build());

    }


}
