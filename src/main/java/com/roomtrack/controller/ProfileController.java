package com.roomtrack.controller;

import com.roomtrack.dto.ProfileResponse;
import com.roomtrack.dto.UpdatePasswordRequest;
import com.roomtrack.dto.UpdateProfileRequest;
import com.roomtrack.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for profile endpoints.
 *
 * GET  /profiles/{id}        — Get profile by user ID
 * GET  /profiles/tenants     — Get all tenants (role=tenant)
 * POST /profiles             — Create or update profile (upsert)
 * PUT  /profiles/password    — Change password (authenticated user only)
 *
 * All endpoints require a valid Bearer JWT token.
 */
@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    /**
     * Get a user's profile by ID.
     * Android call: GET /profiles/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable String id) {
        return ResponseEntity.ok(profileService.getProfile(id));
    }

    /**
     * Get all tenant profiles — used by landlord for tenant dropdowns.
     * Android call: GET /profiles/tenants
     */
    @GetMapping("/tenants")
    public ResponseEntity<List<ProfileResponse>> getTenants() {
        return ResponseEntity.ok(profileService.getTenants());
    }

    /**
     * Create or update a user profile.
     * Android call: POST /profiles
     */
    @PostMapping
    public ResponseEntity<Void> upsertProfile(@RequestBody UpdateProfileRequest request) {
        profileService.upsertProfile(request);
        return ResponseEntity.ok().build();
    }

    /**
     * Change password for the currently authenticated user.
     * Android call: PUT /profiles/password
     * The user ID is extracted from the JWT token (not from request body).
     */
    @PutMapping("/password")
    public ResponseEntity<Void> updatePassword(@RequestBody UpdatePasswordRequest request,
                                               Authentication authentication) {
        // Extract user ID from JWT (set by JwtAuthFilter)
        String userId = (String) authentication.getPrincipal();
        profileService.updatePassword(userId, request);
        return ResponseEntity.ok().build();
    }
}
