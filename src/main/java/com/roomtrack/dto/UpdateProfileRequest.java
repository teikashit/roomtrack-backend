package com.roomtrack.dto;

/**
 * DTO for updating a user's profile.
 * Matches Android's UpdateProfileRequest.kt
 */
public class UpdateProfileRequest {

    private String id;
    private String full_name;
    private String phone;
    private String address;
    private String role;
    private String photo_url;

    public UpdateProfileRequest() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFull_name() { return full_name; }
    public void setFull_name(String full_name) { this.full_name = full_name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getPhoto_url() { return photo_url; }
    public void setPhoto_url(String photo_url) { this.photo_url = photo_url; }
}
