package com.roomtrack.dto;

/**
 * DTO for room creation/update requests.
 * Matches Android's RoomRequest.kt
 */
public class RoomRequest {

    private String unit_name;
    private Double monthly_rate;
    private String description;
    private String status = "vacant";
    private String floor;
    private String size;

    public RoomRequest() {}

    public String getUnit_name() { return unit_name; }
    public void setUnit_name(String unit_name) { this.unit_name = unit_name; }

    public Double getMonthly_rate() { return monthly_rate; }
    public void setMonthly_rate(Double monthly_rate) { this.monthly_rate = monthly_rate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getFloor() { return floor; }
    public void setFloor(String floor) { this.floor = floor; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
}
