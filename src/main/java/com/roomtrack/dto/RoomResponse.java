package com.roomtrack.dto;

/**
 * DTO for room responses.
 * Matches Android's RoomResponse.kt
 */
public class RoomResponse {

    private String id;
    private String unit_name;
    private Double monthly_rate;
    private String status;
    private String floor;
    private String size;
    private String description;
    private String tenant_id;
    private String tenant_name;
    private String photo_url;
    private String created_at;

    public RoomResponse() {}

    // ---- Getters & Setters ----

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUnit_name() { return unit_name; }
    public void setUnit_name(String unit_name) { this.unit_name = unit_name; }

    public Double getMonthly_rate() { return monthly_rate; }
    public void setMonthly_rate(Double monthly_rate) { this.monthly_rate = monthly_rate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getFloor() { return floor; }
    public void setFloor(String floor) { this.floor = floor; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getTenant_id() { return tenant_id; }
    public void setTenant_id(String tenant_id) { this.tenant_id = tenant_id; }

    public String getTenant_name() { return tenant_name; }
    public void setTenant_name(String tenant_name) { this.tenant_name = tenant_name; }

    public String getPhoto_url() { return photo_url; }
    public void setPhoto_url(String photo_url) { this.photo_url = photo_url; }

    public String getCreated_at() { return created_at; }
    public void setCreated_at(String created_at) { this.created_at = created_at; }
}
