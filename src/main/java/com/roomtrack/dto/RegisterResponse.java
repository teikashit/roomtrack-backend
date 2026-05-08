package com.roomtrack.dto;

/**
 * DTO for POST /auth/register response.
 * Matches Android's RegisterResponse.kt: { id, email }
 */
public class RegisterResponse {

    private String id;
    private String email;

    public RegisterResponse() {}

    public RegisterResponse(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
