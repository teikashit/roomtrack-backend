package com.roomtrack.dto;

/**
 * DTO for POST /auth/login response.
 * Matches Android's LoginResponse.kt exactly:
 *   access_token, token_type, user { id, email, user_metadata { full_name, role } }
 */
public class LoginResponse {

    private String access_token;
    private String token_type = "Bearer";
    private UserData user;

    public LoginResponse() {}

    public LoginResponse(String accessToken, UserData user) {
        this.access_token = accessToken;
        this.user = user;
    }

    // ---- Inner class: UserData ----
    public static class UserData {
        private String id;
        private String email;
        private UserMetadata user_metadata;

        public UserData() {}

        public UserData(String id, String email, UserMetadata userMetadata) {
            this.id = id;
            this.email = email;
            this.user_metadata = userMetadata;
        }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public UserMetadata getUser_metadata() { return user_metadata; }
        public void setUser_metadata(UserMetadata user_metadata) { this.user_metadata = user_metadata; }
    }

    // ---- Inner class: UserMetadata ----
    public static class UserMetadata {
        private String full_name;
        private String role;

        public UserMetadata() {}

        public UserMetadata(String fullName, String role) {
            this.full_name = fullName;
            this.role = role;
        }

        public String getFull_name() { return full_name; }
        public void setFull_name(String full_name) { this.full_name = full_name; }

        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }

    // ---- Getters & Setters ----

    public String getAccess_token() { return access_token; }
    public void setAccess_token(String access_token) { this.access_token = access_token; }

    public String getToken_type() { return token_type; }
    public void setToken_type(String token_type) { this.token_type = token_type; }

    public UserData getUser() { return user; }
    public void setUser(UserData user) { this.user = user; }
}
