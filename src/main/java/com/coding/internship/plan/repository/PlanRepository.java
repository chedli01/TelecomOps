package com.coding.internship.plan.repository;

import com.coding.internship.plan.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    @Query(value = "SELECT * from plans p where p.data_quota > (select data_quota from plans where id=:id) order by p.data_quota asc limit 1",nativeQuery = true)
    Plan getNextPlanByDataQuota(@Param("id") Long id);
}
