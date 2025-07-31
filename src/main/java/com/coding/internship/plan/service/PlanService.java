package com.coding.internship.plan.service;

import com.coding.internship.exception.RessourceNotFoundException;
import com.coding.internship.plan.dto.PlanCreateDto;
import com.coding.internship.plan.model.Plan;
import com.coding.internship.plan.repository.PlanRepository;
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
    public Plan updatePlan(PlanCreateDto planCreateDto, Long id){
        Plan plan= getPlanById(id);
        if(planCreateDto.getName()!=null){
            plan.setName(planCreateDto.getName());
        }
        if(planCreateDto.getDescription()!=null){
            plan.setDescription(planCreateDto.getDescription());
        }
        if(planCreateDto.getPrice()!=null){
            plan.setPrice(planCreateDto.getPrice());
        }
        if (planCreateDto.getDataQuota() != null) {
            plan.setDataQuota(planCreateDto.getDataQuota());
        }
        if (planCreateDto.getCallsMinutes() != null) {
            plan.setCallsMinutes(planCreateDto.getCallsMinutes());
        }
        if (planCreateDto.getSmsNumber() != null) {
            plan.setSmsNumber(planCreateDto.getSmsNumber());
        }
        if (planCreateDto.getValidityDays() != null) {
            plan.setValidityDays(planCreateDto.getValidityDays());
        }
        return planRepository.save(plan);
    }

    public void deletePlan(Long id){
        if (!planRepository.existsById(id)) {
            throw new RessourceNotFoundException("plan not found");
        }
        planRepository.deleteById(id);
    }


    public Plan getNextPlanByDataQuota(Long id){
        return planRepository.getNextPlanByDataQuota(id);
    }


}
