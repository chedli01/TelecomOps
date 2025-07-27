package com.coding.internship.plan;

import com.coding.internship.plan.model.Plan;
import com.coding.internship.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plan")
public class PlanController {
    private final PlanService planService;

    @GetMapping("/{id}")
    public Plan getPlanById(Long id){
        return planService.getPlanById(id);
    }
}
