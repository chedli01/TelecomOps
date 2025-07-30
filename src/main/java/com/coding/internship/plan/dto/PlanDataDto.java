package com.coding.internship.plan.dto;

import com.coding.internship.subscription.model.Subscription;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanDataDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Double dataQuota;
    private Double callsMinutes;
    private Integer smsNumber;
    private Integer validityDays;
}
