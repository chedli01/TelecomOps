package com.coding.internship.user.client;

import com.coding.internship.subscription.dto.SubscriptionDataDto;
import com.coding.internship.subscription.mapper.SubscriptionMapper;
import com.coding.internship.user.client.dto.ClientDataDto;
import com.coding.internship.user.client.mapper.ClientMapper;
import com.coding.internship.user.client.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class ClientController {
    private final ClientService clientService;
    private final ClientMapper clientMapper;
    private final SubscriptionMapper subscriptionMapper;

    @GetMapping("/{id}")
    public ClientDataDto findClientById(@PathVariable Long id){
        return clientMapper.mapToDto(clientService.findClientById(id));
    }
    @GetMapping("{id}/subscription")
    public SubscriptionDataDto getClientActiveSub(@PathVariable Long id){
        return subscriptionMapper.mapToDto(clientService.getActiveSub(id));

    }
}
