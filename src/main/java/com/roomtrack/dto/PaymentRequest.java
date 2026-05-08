package com.roomtrack.dto;

/**
 * DTO for payment creation requests.
 * Matches Android's PaymentRequest.kt
 */
public class PaymentRequest {

    private String tenant_id;
    private String tenant_name;
    private String room_id;
    private Double amount;
    private String status = "Pending";
    private String due_date;
    private String description;

    public PaymentRequest() {}

    public String getTenant_id() { return tenant_id; }
    public void setTenant_id(String tenant_id) { this.tenant_id = tenant_id; }

    public String getTenant_name() { return tenant_name; }
    public void setTenant_name(String tenant_name) { this.tenant_name = tenant_name; }

    public String getRoom_id() { return room_id; }
    public void setRoom_id(String room_id) { this.room_id = room_id; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDue_date() { return due_date; }
    public void setDue_date(String due_date) { this.due_date = due_date; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
