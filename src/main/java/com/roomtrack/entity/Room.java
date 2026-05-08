package com.roomtrack.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Entity for the "rooms" table in Supabase PostgreSQL.
 */
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private String id;

    @Column(name = "unit_name", nullable = false)
    private String unitName;

    @Column(name = "monthly_rate", nullable = false)
    private Double monthlyRate;

    @Column(name = "status", nullable = false)
    private String status; // "vacant" or "occupied"

    @Column(name = "floor")
    private String floor;

    @Column(name = "size")
    private String size;

    @Column(name = "description")
    private String description;

    @Column(name = "tenant_id")
    private String tenantId;

    @Column(name = "tenant_name")
    private String tenantName;

    @Column(name = "photo_url")
    private String photoUrl;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // ---- Constructors ----

    public Room() {}

    // ---- Getters & Setters ----

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUnitName() { return unitName; }
    public void setUnitName(String unitName) { this.unitName = unitName; }

    public Double getMonthlyRate() { return monthlyRate; }
    public void setMonthlyRate(Double monthlyRate) { this.monthlyRate = monthlyRate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getFloor() { return floor; }
    public void setFloor(String floor) { this.floor = floor; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }

    public String getTenantName() { return tenantName; }
    public void setTenantName(String tenantName) { this.tenantName = tenantName; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
