package com.coding.internship.subscription.repository;

import com.coding.internship.subscription.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
