package com.roomtrack.dto;

/**
 * DTO for profile responses.
 * Matches Android's ProfileResponse.kt
 */
public class ProfileResponse {

    private String id;
    private String full_name;
    private String phone;
    private String address;
    private String role;
    private String photo_url;

    public ProfileResponse() {}

    public ProfileResponse(String id, String fullName, String phone,
                           String address, String role, String photoUrl) {
        this.id = id;
        this.full_name = fullName;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.photo_url = photoUrl;
    }

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
