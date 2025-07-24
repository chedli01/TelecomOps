package com.coding.internship.plan.repository;

import com.coding.internship.plan.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
