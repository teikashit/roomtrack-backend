package com.roomtrack.service;

import com.roomtrack.dto.*;
import com.roomtrack.entity.Profile;
import com.roomtrack.repository.ProfileRepository;
import com.roomtrack.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {
        Profile profile = profileRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), profile.getPasswordHash())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        String userId = profile.getIdAsString();
        String token = jwtUtil.generateToken(userId, profile.getEmail(), profile.getRole());

        LoginResponse.UserMetadata metadata = new LoginResponse.UserMetadata(
                profile.getFullName(), profile.getRole());
        LoginResponse.UserData userData = new LoginResponse.UserData(
                userId, profile.getEmail(), metadata);

        return new LoginResponse(token, userData);
    }

    public RegisterResponse register(RegisterRequest request) {
        if (profileRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        UUID userId = UUID.randomUUID();

        RegisterRequest.RegisterMetadata meta = request.getData();

        Profile profile = new Profile();
        profile.setId(userId);
        profile.setEmail(request.getEmail());
        profile.setPasswordHash(hashedPassword);
        profile.setFullName(meta != null ? meta.getFull_name() : null);
        profile.setPhone(meta != null ? meta.getPhone() : null);
        profile.setRole(meta != null && meta.getRole() != null ? meta.getRole() : "tenant");

        profileRepository.save(profile);

        return new RegisterResponse(userId.toString(), profile.getEmail());
    }
}
