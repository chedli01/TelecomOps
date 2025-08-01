package com.coding.internship.user.client.service;

import com.coding.internship.subscription.enums.SubscriptionStatus;
import com.coding.internship.subscription.model.Subscription;
import com.coding.internship.user.client.dto.ClientDataDto;
import com.coding.internship.user.client.dto.UpdateClientDto;
import com.coding.internship.user.client.mapper.ClientMapper;
import com.coding.internship.user.client.model.Client;
import com.coding.internship.user.client.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
    public List<Subscription> getSubscriptions(Long id){
        Client client = findClientById(id);
        return client.getSubscriptions();
    }
    public Optional<Subscription> getLatestInactiveSub(Long id){
        List<Subscription> subscriptions = getSubscriptions(id);
        Optional<Subscription> latestInactive = subscriptions.stream()
                .filter(sub -> !sub.getStatus().equals(SubscriptionStatus.ACTIVE))
                .max(Comparator.comparing(Subscription::getEndDate));

        return latestInactive;

    }
    public Double getClientTotalCallMinutes(Long id){
        List<Subscription> subscriptions = getSubscriptions(id);
        Double totalCallMinutes = 0.0;
        for (Subscription subscription : subscriptions){
            totalCallMinutes+=subscription.getPlan().getCallsMinutes();

        }
        return totalCallMinutes;


    }
    public Client updateClient(Long id, UpdateClientDto updateClientDto){
        Client client = findClientById(id);
        if(updateClientDto.getFirstName()!=null){
            client.setFirstName(updateClientDto.getFirstName());
        }
        if(updateClientDto.getLastName()!=null){
            client.setLastName(updateClientDto.getLastName());
        }
        if(updateClientDto.getAddress()!=null){
            client.setAddress(updateClientDto.getAddress());
        }
        if(updateClientDto.getCin()!=null){
            client.setCin(updateClientDto.getCin());
        }
        if(updateClientDto.getPhoneNumber()!=null){
            client.setPhoneNumber(updateClientDto.getPhoneNumber());
        }
        if(updateClientDto.getIsStudent()!=null){
            client.setIsStudent(updateClientDto.getIsStudent());
        }
        if(updateClientDto.getEmail()!=null){
            client.setEmail(updateClientDto.getEmail());
        }
        if(updateClientDto.getClientType()!=null){
            client.setType(updateClientDto.getClientType());
        }
        return clientRepository.save(client);
    }

}
