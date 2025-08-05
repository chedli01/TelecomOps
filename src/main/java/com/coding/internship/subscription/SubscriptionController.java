package com.coding.internship.subscription;

import com.coding.internship.subscription.dto.SubscriptionDataDto;
import com.coding.internship.subscription.mapper.SubscriptionMapper;
import com.coding.internship.subscription.service.SubscriptionService;
import com.coding.internship.user.client.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscription")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper subscriptionMapper;

    @PostMapping("/{planId}")
    public SubscriptionDataDto subscribeToPlan(@PathVariable Long planId, @AuthenticationPrincipal Client client){
        return subscriptionMapper.mapToDto(subscriptionService.subscribeToPlan(planId, client.getId()));


    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public List<SubscriptionDataDto> getAllSubscriptions(){
        return subscriptionService.getAllSubscriptions().stream().map(subscriptionMapper::mapToDto).toList();
    }
    @GetMapping("/{id}")
    @PreAuthorize("@resourceAccess.hasAccessToResource(@subscriptionRepository.findById(#id).orElse(null))")
    public SubscriptionDataDto getSubscriptionById(@PathVariable Long id){
        return subscriptionMapper.mapToDto(subscriptionService.getSubById(id));
    }
    @PostMapping("/call/{minutes}")
    public SubscriptionDataDto makeCall(@AuthenticationPrincipal Client client,@PathVariable Double minutes){
        return subscriptionMapper.mapToDto(subscriptionService.makeCall(client.getId(),minutes));

    }
    @PostMapping("/sms")
    public SubscriptionDataDto makeSms(@AuthenticationPrincipal Client client){
        return subscriptionMapper.mapToDto(subscriptionService.makeSms(client.getId()));
    }
    @PostMapping("/data/{quantity}")
    public SubscriptionDataDto useData(@AuthenticationPrincipal Client client,@PathVariable Double quantity){
        return subscriptionMapper.mapToDto(subscriptionService.consumeData(client.getId(),quantity));
    }
    @PostMapping("/cancel")
    public SubscriptionDataDto cancelSubscription(@AuthenticationPrincipal Client client){
        return subscriptionMapper.mapToDto(subscriptionService.cancelSub(client.getId()));

    }
}
