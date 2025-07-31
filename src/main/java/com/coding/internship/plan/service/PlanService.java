package com.coding.internship.plan.service;

import com.coding.internship.plan.dto.PlanCreateDto;
import com.coding.internship.plan.model.Plan;
import com.coding.internship.plan.repository.PlanRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanService {
    private final PlanRepository planRepository;

    public Plan getPlanById(Long id){
        return planRepository.findById(id).orElseThrow(()->new RuntimeException("plan not found"));
    }

    public Plan createPlan(PlanCreateDto planCreateDto){
        Plan plan = Plan.builder()
                .name(planCreateDto.getName())
                .description(planCreateDto.getDescription())
                .price(planCreateDto.getPrice())
                .dataQuota(planCreateDto.getDataQuota())
                .callsMinutes(planCreateDto.getCallsMinutes())
                .smsNumber(planCreateDto.getSmsNumber())
                .validityDays(planCreateDto.getValidityDays())
                .build();

        return planRepository.save(plan);

    }


    public Plan getNextPlanByDataQuota(Long id){
        return planRepository.getNextPlanByDataQuota(id);
    }


}
