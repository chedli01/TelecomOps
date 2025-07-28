package com.coding.internship.user.client.service;

import com.coding.internship.subscription.enums.SubscriptionStatus;
import com.coding.internship.subscription.model.Subscription;
import com.coding.internship.user.client.dto.ClientDataDto;
import com.coding.internship.user.client.mapper.ClientMapper;
import com.coding.internship.user.client.model.Client;
import com.coding.internship.user.client.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public Client findClientById(Long id){
       return  clientRepository.findById(id).orElseThrow(()->new RuntimeException("client not found"));

    }
    public Subscription getActiveSub(Long id){
        Client client = findClientById(id);
        for (Subscription subscription : client.getSubscriptions()){
            if (subscription.getStatus().equals(SubscriptionStatus.ACTIVE)){
                return subscription;
            }
        }
        return null;
    }

}
