package com.coding.internship.plan.service;

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


}
