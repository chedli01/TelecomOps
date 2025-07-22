package com.coding.internship.user.client.service;

import com.coding.internship.user.client.model.Client;
import com.coding.internship.user.client.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Client findClientById(Long id){
        return clientRepository.findById(id).orElseThrow(()->new RuntimeException("client not found"));
    }

}
