package com.coding.internship.user.client;

import com.coding.internship.subscription.dto.SubscriptionDataDto;
import com.coding.internship.subscription.mapper.SubscriptionMapper;
import com.coding.internship.user.client.dto.ClientDataDto;
import com.coding.internship.user.client.dto.UpdateClientDto;
import com.coding.internship.user.client.mapper.ClientMapper;
import com.coding.internship.user.client.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/{id}/subscriptions")
    public List<SubscriptionDataDto> getClientActiveSubs(@PathVariable Long id){
        return clientService.getSubscriptions(id).stream().map(subscriptionMapper::mapToDto).toList();
    }
    @GetMapping("{id}/sub/inactive")
    public SubscriptionDataDto getClientLatestInactiveSub(@PathVariable Long id){
        return subscriptionMapper.mapToDto(clientService.getLatestInactiveSub(id).orElse(null));
    }
    @GetMapping("{id}/calls")
    public Double getClientTotalCallMinutes(@PathVariable Long id){
        return clientService.getClientTotalCallMinutes(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ClientDataDto updateClient(@PathVariable Long id, @RequestBody UpdateClientDto updateClientDto){
        return clientMapper.mapToDto(clientService.updateClient(id,updateClientDto));
    }
}
