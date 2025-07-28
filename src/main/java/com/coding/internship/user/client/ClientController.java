package com.coding.internship.user.client;

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

    @GetMapping("/{id}")
    public ClientDataDto findClientById(@PathVariable Long id){
        return clientMapper.mapToDto(clientService.findClientById(id));
    }
}
