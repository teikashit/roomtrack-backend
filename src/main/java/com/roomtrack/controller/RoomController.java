package com.roomtrack.controller;

import com.roomtrack.dto.AssignTenantRequest;
import com.roomtrack.dto.RoomRequest;
import com.roomtrack.dto.RoomResponse;
import com.roomtrack.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for room endpoints.
 *
 * GET   /rooms                    — Get all rooms
 * GET   /rooms/tenant/{tenantId}  — Get room by tenant ID
 * POST  /rooms                    — Create a room
 * PATCH /rooms/{id}/assign        — Assign a tenant to a room
 * PATCH /rooms/{id}/unassign      — Remove tenant from room
 *
 * All endpoints require a valid Bearer JWT token.
 */
@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    /**
     * Get all rooms ordered by unit name.
     * Android call: GET /rooms
     */
    @GetMapping
    public ResponseEntity<List<RoomResponse>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    /**
     * Get the room assigned to a specific tenant.
     * Android call: GET /rooms/tenant/{tenantId}
     */
    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<RoomResponse>> getRoomByTenant(@PathVariable String tenantId) {
        return ResponseEntity.ok(roomService.getRoomByTenant(tenantId));
    }

    /**
     * Create a new room.
     * Returns List<RoomResponse> to match Android's expected response type.
     * Android call: POST /rooms
     */
    @PostMapping
    public ResponseEntity<List<RoomResponse>> createRoom(@RequestBody RoomRequest request) {
        return ResponseEntity.ok(roomService.createRoom(request));
    }

    /**
     * Assign a tenant to a room.
     * Android call: PATCH /rooms/{id}/assign
     */
    @PatchMapping("/{id}/assign")
    public ResponseEntity<Void> assignTenant(@PathVariable String id,
                                              @RequestBody AssignTenantRequest request) {
        roomService.assignTenant(id, request);
        return ResponseEntity.ok().build();
    }

    /**
     * Unassign the tenant from a room (mark as vacant).
     * Android call: PATCH /rooms/{id}/unassign
     */
    @PatchMapping("/{id}/unassign")
    public ResponseEntity<Void> unassignTenant(@PathVariable String id) {
        roomService.unassignTenant(id);
        return ResponseEntity.ok().build();
    }
}
