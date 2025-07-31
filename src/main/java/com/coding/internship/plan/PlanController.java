package com.coding.internship.plan;

import com.coding.internship.plan.dto.PlanCreateDto;
import com.coding.internship.plan.dto.PlanDataDto;
import com.coding.internship.plan.mapper.PlanMapper;
import com.coding.internship.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plan")
public class PlanController {
    private final PlanService planService;
    private final PlanMapper planMapper;

    @GetMapping("/{id}")
    public PlanDataDto getPlanById(@PathVariable  Long id){
        return planMapper.mapToDto(planService.getPlanById(id));
    }
    @PostMapping
    public PlanDataDto createPlan(@RequestBody PlanCreateDto planCreateDto){
        return planMapper.mapToDto(planService.createPlan(planCreateDto)) ;
    }
    @PutMapping("/{id}")
    public PlanDataDto updatePlan(@RequestBody PlanCreateDto planCreateDto,@PathVariable  Long id){
        return planMapper.mapToDto(planService.updatePlan(planCreateDto,id)) ;
    }
    @DeleteMapping("/{id}")
    public String deletePlanById(@PathVariable  Long id){
        planService.deletePlan(id);
        return "plan with id " + id + " deleted";

    }

    @GetMapping("/next/{id}")
    public PlanDataDto getNextPlanByDataQuota(@PathVariable  Long id){
        return planMapper.mapToDto(planService.getNextPlanByDataQuota(id)) ;
    }
}
