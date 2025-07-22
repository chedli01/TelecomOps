package com.coding.internship.user.client.mapper;


import com.coding.internship.user.client.dto.ClientDataDto;
import com.coding.internship.user.client.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client mapToEntity(ClientDataDto clientDataDto);
    ClientDataDto mapToDto(Client client);
}
