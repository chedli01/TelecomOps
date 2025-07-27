package com.coding.internship.subscription;

import com.coding.internship.subscription.dto.SubscriptionDataDto;
import com.coding.internship.subscription.service.SubscriptionService;
import com.coding.internship.user.client.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscription")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping("/{planId}")
    public SubscriptionDataDto subscribeToPlan(@PathVariable Long planId, @AuthenticationPrincipal Client client){
        return subscriptionService.subscribeToPlan(planId, client.getId());


    }
    @GetMapping
    public List<SubscriptionDataDto> getAllSubscriptions(){
        return subscriptionService.getAllSubscriptions();
    }
    @PostMapping("/call/{minutes}")
    public SubscriptionDataDto makeCall(@AuthenticationPrincipal Client client,@PathVariable Double minutes){
        return subscriptionService.makeCall(client.getId(),minutes);

    }
    @PostMapping("/sms")
    public SubscriptionDataDto makeSms(@AuthenticationPrincipal Client client){
        return subscriptionService.makeSms(client.getId());
    }
    @PostMapping("/data/{quantity}")
    public SubscriptionDataDto useData(@AuthenticationPrincipal Client client,@PathVariable Double quantity){
        return subscriptionService.consumeData(client.getId(),quantity);
    }
}
