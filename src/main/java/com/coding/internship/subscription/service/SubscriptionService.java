package com.coding.internship.subscription.service;

import com.coding.internship.drools.dto.*;
import com.coding.internship.drools.service.DroolsService;
import com.coding.internship.exception.RessourceNotFoundException;
import com.coding.internship.invoice.dto.InvoiceCreateDto;
import com.coding.internship.invoice.dto.InvoiceUpdateDto;
import com.coding.internship.invoice.enums.InvoiceStatus;
import com.coding.internship.invoice.service.InvoiceService;
import com.coding.internship.notification.email.EmailService;
import com.coding.internship.notification.sms.service.SmsService;
import com.coding.internship.order.dto.OrderCreateDto;
import com.coding.internship.order.dto.OrderItemCreateDto;
import com.coding.internship.order.model.OrderItem;
import com.coding.internship.order.service.OrderService;
import com.coding.internship.plan.model.Plan;
import com.coding.internship.plan.service.PlanService;
import com.coding.internship.product.model.Product;
import com.coding.internship.product.service.ProductService;
import com.coding.internship.subscription.dto.SubscriptionUpdateDto;
import com.coding.internship.subscription.enums.SubscriptionStatus;
import com.coding.internship.subscription.model.Subscription;
import com.coding.internship.subscription.repository.SubscriptionRepository;
import com.coding.internship.user.client.dto.UpdateClientDto;
import com.coding.internship.user.client.enums.ClientType;
import com.coding.internship.user.client.model.Client;
import com.coding.internship.user.client.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final PlanService planService;
    private final ClientService clientService;
    private final InvoiceService invoiceService;
    private final DroolsService droolsService;
    private final EmailService emailService;
    private final SmsService smsService;
    private final ProductService productService;

    public Subscription subscribeToPlan(Long planId, Long clientId){
        Plan plan = planService.getPlanById(planId);
        Client client = clientService.findClientById(clientId);

        Subscription activeSub = clientService.getActiveSub(clientId);
        if(activeSub!=null){
            updateSubscription(activeSub.getId(),SubscriptionUpdateDto.builder().status(SubscriptionStatus.INACTIVE).build());
        }

        var subscription = Subscription.builder()
                           .plan(plan)
                           .client(client).startDate(LocalDateTime.now()).endDate(LocalDateTime.now().plusDays(plan.getValidityDays()))
                .remainingData(plan.getDataQuota()).remainingCalls(plan.getCallsMinutes()).remainingSms(plan.getSmsNumber()).discount(0.0).status(SubscriptionStatus.ACTIVE)
                .build();
        Subscription savedSub = subscriptionRepository.save(droolsService.applyProcessOnSub(subscription));
        System.out.println("type"+ client.getType());
        if(client.getType().equals(ClientType.CUSTOMER)){
            clientService.updateClient(clientId, UpdateClientDto.builder().clientType(ClientType.SUBSCRIBER).build());
        }
        UUID uuid = UUID.randomUUID();


        invoiceService.createSubInvoice(savedSub, InvoiceCreateDto.builder().invoiceNumber(uuid.toString()).description(plan.getDescription()).dueDate(savedSub.getEndDate().minusDays(3L)).status(InvoiceStatus.UNPAID).total(plan.getPrice()-savedSub.getDiscount()).build());
        return savedSub;



    }

    public List<Subscription> getAllSubscriptions(){
        return subscriptionRepository.findAll();
    }

    public Subscription makeCall(Long clientId,Double minutes){
        Subscription subscription = clientService.getActiveSub(clientId);
        CallVerificationRequest callVerificationRequest = CallVerificationRequest.builder().minutesConsumed(minutes).totalConsumed(900.0).build();
        VerificationResult verificationResult =droolsService.verifyCalls(subscription,callVerificationRequest);
        if(!verificationResult.isValid()){
            throw new IllegalArgumentException("not valid call transaction");
        }
        if (verificationResult.isGetGift()){
            System.out.println("gift logic");
            Product giftedProduct = productService.findGiftedProduct();
            System.out.println("you ve won a "+giftedProduct.getName());
//            OrderItemCreateDto orderItemCreateDto = OrderItemCreateDto.builder().productId(giftedProduct.getId()).quantity(1).build();
//            orderService.passFreeOrder(OrderCreateDto.builder().description(giftedProduct.getDescription()).
//                    orderItems(List.of(orderItemCreateDto))
//                    .build(),clientId);





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
        Plan plan = subscription.getPlan();
        Client client = subscription.getClient();
        DataVerificationRequest dataVerificationRequest = DataVerificationRequest.builder().consumedData(data).totalData(clientService.getClientTotalData(clientId)).build();

        DataVerificationResult dataVerificationResult = droolsService.verifyData(subscription,dataVerificationRequest);
        System.out.println("consumed total "+dataVerificationRequest.getTotalData());

        if(dataVerificationResult.isUpgradeClientToVip()){
            clientService.updateClient(clientId,UpdateClientDto.builder().clientType(ClientType.VIP).build());


        }


        if(!dataVerificationResult.isVerified()){
            throw new IllegalArgumentException("not valid transaction");
        }
        if(dataVerificationResult.isSendSmsAlert()){
            System.out.println("send sms logic");
            smsService.sendSms(client.getPhoneNumber(),"You have exceeded 80% of your data quota");


        }
        if(dataVerificationResult.isSendEmailUpgradeRecommendation()){
            Plan recommendedPlan = planService.getNextPlanByDataQuota(plan.getId());
            System.out.println("send email logic : " + recommendedPlan.getDescription());
            Map<String, Object> variables = new HashMap<>();
            variables.put("name", client.getFirstName() +" "+ client.getLastName());
            variables.put("currentPlanName",plan.getName() );
            variables.put("currentPlanDescription",plan.getDescription());
            variables.put("currentPlanPrice",plan.getPrice());
            variables.put("currentPlanDataQuota",plan.getDataQuota());
            variables.put("currentPlanCallsMinutes",plan.getCallsMinutes());
            variables.put("currentPlanSmsNumber",plan.getSmsNumber());
            variables.put("currentPlanValidityDays",plan.getValidityDays());



            variables.put("suggestedPlanName",recommendedPlan.getName() );
            variables.put("suggestedPlanDescription",recommendedPlan.getDescription());
            variables.put("suggestedPlanPrice",recommendedPlan.getPrice());
            variables.put("suggestedPlanDataQuota",recommendedPlan.getDataQuota());
            variables.put("suggestedPlanCallsMinutes",recommendedPlan.getCallsMinutes());
            variables.put("suggestedPlanSmsNumber",recommendedPlan.getSmsNumber());
            variables.put("suggestedPlanValidityDays",recommendedPlan.getValidityDays());

            emailService.sendHtmlEmail(client.getEmail(),"Plan Upgrade",variables);

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
    public Subscription getSubById(Long id){
        return subscriptionRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("subscription not found"));

    }


}
