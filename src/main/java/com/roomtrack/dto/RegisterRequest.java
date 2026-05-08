package com.roomtrack.dto;

/**
 * DTO for POST /auth/register request.
 * Matches Android's RegisterRequest.kt:
 *   email, password, data { full_name, phone, role }
 */
public class RegisterRequest {

    private String email;
    private String password;
    private RegisterMetadata data;

    public RegisterRequest() {}

    // ---- Inner class: RegisterMetadata ----
    public static class RegisterMetadata {
        private String full_name;
        private String phone;
        private String role;

        public RegisterMetadata() {}

        public String getFull_name() { return full_name; }
        public void setFull_name(String full_name) { this.full_name = full_name; }

        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }

        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }

    // ---- Getters & Setters ----

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public RegisterMetadata getData() { return data; }
    public void setData(RegisterMetadata data) { this.data = data; }
}
