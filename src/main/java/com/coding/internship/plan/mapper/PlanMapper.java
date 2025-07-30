package com.coding.internship.plan.mapper;

import com.coding.internship.generic.GenericMapper;
import com.coding.internship.plan.dto.PlanDataDto;
import com.coding.internship.plan.model.Plan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlanMapper extends GenericMapper<Plan, PlanDataDto> {
}
