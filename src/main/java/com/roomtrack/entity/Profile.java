package com.roomtrack.entity;

import jakarta.persistence.*;
import java.util.UUID;

/**
 * Entity for the "profiles" table in Supabase PostgreSQL.
 * Uses UUID type to match Supabase's uuid column exactly.
 */
@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @Column(nullable = false)
    private UUID id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "email")
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    public Profile() {}

    // Getters & Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    // Helper to get/set id as String
    public String getIdAsString() { return id != null ? id.toString() : null; }
    public void setIdFromString(String id) { this.id = id != null ? UUID.fromString(id) : null; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
}
