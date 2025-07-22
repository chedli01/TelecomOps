package com.coding.internship.user.client.repository;

import com.coding.internship.user.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
