package com.roomtrack.repository;

import com.roomtrack.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Payment entity.
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {

    List<Payment> findAllByOrderByDueDateDesc();

    List<Payment> findByTenantIdOrderByDueDateDesc(String tenantId);
}
