package com.roomtrack.controller;

import com.roomtrack.dto.LoginRequest;
import com.roomtrack.dto.LoginResponse;
import com.roomtrack.dto.RegisterRequest;
import com.roomtrack.dto.RegisterResponse;
import com.roomtrack.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for authentication endpoints.
 *
 * POST /auth/login   — Login and receive JWT token
 * POST /auth/register — Register a new user
 *
 * These endpoints are PUBLIC — no JWT required (configured in SecurityConfig).
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Login with email and password.
     * Returns JWT token + user info matching Android's LoginResponse model.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Register a new user with email, password, and metadata (name, phone, role).
     * Returns the new user's ID and email matching Android's RegisterResponse model.
     */
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        RegisterResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }
}
