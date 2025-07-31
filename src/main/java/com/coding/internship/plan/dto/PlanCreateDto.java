package com.coding.internship.plan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanCreateDto {
    private String name;
    private String description;
    private Double price;
    private Double dataQuota;
    private Double callsMinutes;
    private Integer smsNumber;
    private Integer validityDays;
}
