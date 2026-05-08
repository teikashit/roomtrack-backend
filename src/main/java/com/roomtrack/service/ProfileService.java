package com.roomtrack.service;

import com.roomtrack.dto.ProfileResponse;
import com.roomtrack.dto.UpdatePasswordRequest;
import com.roomtrack.dto.UpdateProfileRequest;
import com.roomtrack.entity.Profile;
import com.roomtrack.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ProfileResponse getProfile(String id) {
        Profile profile = profileRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Profile not found"));
        return mapToResponse(profile);
    }

    public List<ProfileResponse> getTenants() {
        return profileRepository.findByRole("tenant")
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public void upsertProfile(UpdateProfileRequest request) {
        UUID uuid = UUID.fromString(request.getId());
        Profile profile = profileRepository.findById(uuid).orElse(new Profile());
        profile.setId(uuid);
        profile.setFullName(request.getFull_name());
        profile.setPhone(request.getPhone());
        profile.setAddress(request.getAddress());
        profile.setRole(request.getRole());
        if (request.getPhoto_url() != null) {
            profile.setPhotoUrl(request.getPhoto_url());
        }
        profileRepository.save(profile);
    }

    public void updatePassword(String userId, UpdatePasswordRequest request) {
        Profile profile = profileRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Profile not found"));
        profile.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        profileRepository.save(profile);
    }

    private ProfileResponse mapToResponse(Profile profile) {
        return new ProfileResponse(
                profile.getIdAsString(),
                profile.getFullName(),
                profile.getPhone(),
                profile.getAddress(),
                profile.getRole(),
                profile.getPhotoUrl()
        );
    }
}
