package com.roomtrack.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("app", "RoomTrack Backend");
        response.put("status", "running");
        response.put("version", "1.0.0");
        response.put("endpoints", new String[]{
            "POST /auth/login",
            "POST /auth/register",
            "GET  /profiles/{id}",
            "GET  /profiles/tenants",
            "POST /profiles",
            "PUT  /profiles/password",
            "GET  /rooms",
            "GET  /rooms/tenant/{tenantId}",
            "POST /rooms",
            "PATCH /rooms/{id}/assign",
            "PATCH /rooms/{id}/unassign",
            "GET  /payments",
            "GET  /payments/tenant/{tenantId}",
            "POST /payments",
            "PATCH /payments/{id}/status",
            "GET  /announcements",
            "POST /announcements",
            "DELETE /announcements/{id}"
        });
        return response;
    }
}
