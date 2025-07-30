package com.coding.internship.plan.model;

import com.coding.internship.subscription.model.Subscription;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "plans")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Double dataQuota;
    private Double callsMinutes;
    private Integer smsNumber;
    private Integer validityDays;
    @OneToMany(mappedBy = "plan",fetch = FetchType.LAZY)
    private List<Subscription> subscriptions;

}
