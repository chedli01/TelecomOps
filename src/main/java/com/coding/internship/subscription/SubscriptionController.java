package com.coding.internship.subscription;

import com.coding.internship.subscription.dto.SubscriptionDataDto;
import com.coding.internship.subscription.model.Subscription;
import com.coding.internship.subscription.service.SubscriptionService;
import com.coding.internship.user.client.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription/{planId}")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping
    public SubscriptionDataDto subscribeToPlan(@PathVariable Long planId, @AuthenticationPrincipal Client client){
        return subscriptionService.subscribeToPlan(planId, client.getId());


    }
}
