package com.coding.internship.user.admin.mapper;

import com.coding.internship.user.admin.dto.AdminDataDto;
import com.coding.internship.user.admin.model.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    AdminDataDto mapToDto(Admin admin);
    Admin mapToEntity(AdminDataDto adminDataDto);

}
