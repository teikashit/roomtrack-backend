package com.roomtrack.service;

import com.roomtrack.dto.PaymentRequest;
import com.roomtrack.dto.PaymentResponse;
import com.roomtrack.entity.Payment;
import com.roomtrack.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service for payment operations.
 */
@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * Get all payments, ordered by due date descending.
     * Mirrors: GET rest/v1/payments?order=due_date.desc
     */
    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAllByOrderByDueDateDesc()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get payments for a specific tenant, ordered by due date descending.
     * Mirrors: GET rest/v1/payments?tenant_id=eq.{tenantId}&order=due_date.desc
     */
    public List<PaymentResponse> getPaymentsByTenant(String tenantId) {
        return paymentRepository.findByTenantIdOrderByDueDateDesc(tenantId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Create a new payment record.
     * Mirrors: POST rest/v1/payments
     * Returns: List<PaymentResponse> to match Android's expected response type
     */
    public List<PaymentResponse> createPayment(PaymentRequest request) {
        Payment payment = new Payment();
        payment.setTenantId(request.getTenant_id());
        payment.setTenantName(request.getTenant_name());
        payment.setRoomId(request.getRoom_id());
        payment.setAmount(request.getAmount());
        payment.setStatus(request.getStatus() != null ? request.getStatus() : "Pending");
        payment.setDueDate(request.getDue_date());
        payment.setDescription(request.getDescription());

        Payment saved = paymentRepository.save(payment);
        return List.of(mapToResponse(saved));
    }

    /**
     * Update the status of a payment (e.g., "Paid", "For Verification").
     * Also sets paid_date to today when status is "Paid".
     * Mirrors: PATCH rest/v1/payments?id=eq.{id}
     */
    public void updateStatus(String paymentId, Map<String, String> body) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Payment not found"));

        String newStatus = body.get("status");
        if (newStatus != null) {
            payment.setStatus(newStatus);

            // Auto-set paid_date when marking as Paid
            if ("Paid".equalsIgnoreCase(newStatus)) {
                payment.setPaidDate(LocalDate.now().toString());
            }
        }

        paymentRepository.save(payment);
    }

    // ---- Helper: map Payment entity to PaymentResponse DTO ----

    private PaymentResponse mapToResponse(Payment payment) {
        PaymentResponse resp = new PaymentResponse();
        resp.setId(payment.getId());
        resp.setTenant_id(payment.getTenantId());
        resp.setTenant_name(payment.getTenantName());
        resp.setRoom_id(payment.getRoomId());
        resp.setAmount(payment.getAmount());
        resp.setStatus(payment.getStatus());
        resp.setDue_date(payment.getDueDate());
        resp.setPaid_date(payment.getPaidDate());
        resp.setDescription(payment.getDescription());
        return resp;
    }
}
