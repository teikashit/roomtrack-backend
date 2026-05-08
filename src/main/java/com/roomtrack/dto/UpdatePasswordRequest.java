package com.roomtrack.dto;

/**
 * DTO for changing a user's password.
 * Matches Android's UpdatePasswordRequest.kt
 */
public class UpdatePasswordRequest {

    private String password;

    public UpdatePasswordRequest() {}

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
