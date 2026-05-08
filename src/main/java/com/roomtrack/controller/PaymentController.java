package com.roomtrack.controller;

import com.roomtrack.dto.PaymentRequest;
import com.roomtrack.dto.PaymentResponse;
import com.roomtrack.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller for payment endpoints.
 *
 * GET   /payments                    — Get all payments
 * GET   /payments/tenant/{tenantId}  — Get payments for a tenant
 * POST  /payments                    — Create a payment
 * PATCH /payments/{id}/status        — Update payment status
 *
 * All endpoints require a valid Bearer JWT token.
 */
@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * Get all payments (landlord view), newest first.
     * Android call: GET /payments
     */
    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    /**
     * Get payments for a specific tenant.
     * Android call: GET /payments/tenant/{tenantId}
     */
    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<PaymentResponse>> getPaymentsByTenant(@PathVariable String tenantId) {
        return ResponseEntity.ok(paymentService.getPaymentsByTenant(tenantId));
    }

    /**
     * Create a new payment record.
     * Returns List<PaymentResponse> to match Android's expected response type.
     * Android call: POST /payments
     */
    @PostMapping
    public ResponseEntity<List<PaymentResponse>> createPayment(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.createPayment(request));
    }

    /**
     * Update a payment's status.
     * Accepted statuses: "Paid", "For Verification", "Pending"
     * Android call: PATCH /payments/{id}/status
     * Body: { "status": "Paid" }
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable String id,
                                              @RequestBody Map<String, String> body) {
        paymentService.updateStatus(id, body);
        return ResponseEntity.ok().build();
    }
}
