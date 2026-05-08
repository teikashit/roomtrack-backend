package com.roomtrack.service;

import com.roomtrack.dto.AssignTenantRequest;
import com.roomtrack.dto.RoomRequest;
import com.roomtrack.dto.RoomResponse;
import com.roomtrack.entity.Room;
import com.roomtrack.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for room CRUD operations.
 */
@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    /**
     * Get all rooms, ordered by unit name (A→Z).
     * Mirrors: GET rest/v1/rooms?order=unit_name.asc
     */
    public List<RoomResponse> getAllRooms() {
        return roomRepository.findAllByOrderByUnitNameAsc()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get the room assigned to a specific tenant.
     * Mirrors: GET rest/v1/rooms?tenant_id=eq.{tenantId}
     */
    public List<RoomResponse> getRoomByTenant(String tenantId) {
        return roomRepository.findByTenantId(tenantId)
                .map(room -> List.of(mapToResponse(room)))
                .orElse(List.of());
    }

    /**
     * Create a new room.
     * Mirrors: POST rest/v1/rooms
     * Returns: List<RoomResponse> to match Android's expected response type
     */
    public List<RoomResponse> createRoom(RoomRequest request) {
        Room room = new Room();
        room.setUnitName(request.getUnit_name());
        room.setMonthlyRate(request.getMonthly_rate());
        room.setDescription(request.getDescription());
        room.setStatus(request.getStatus() != null ? request.getStatus() : "vacant");
        room.setFloor(request.getFloor());
        room.setSize(request.getSize());

        Room saved = roomRepository.save(room);
        return List.of(mapToResponse(saved));
    }

    /**
     * Assign a tenant to a room and mark it as "occupied".
     * Mirrors: PATCH rest/v1/rooms?id=eq.{id} (assign)
     */
    public void assignTenant(String roomId, AssignTenantRequest request) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Room not found"));

        room.setTenantId(request.getTenant_id());
        room.setTenantName(request.getTenant_name());
        room.setStatus(request.getStatus() != null ? request.getStatus() : "occupied");
        roomRepository.save(room);
    }

    /**
     * Unassign the tenant from a room and mark it as "vacant".
     * Mirrors: PATCH rest/v1/rooms?id=eq.{id} (unassign)
     */
    public void unassignTenant(String roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Room not found"));

        room.setTenantId(null);
        room.setTenantName(null);
        room.setStatus("vacant");
        roomRepository.save(room);
    }

    // ---- Helper: map Room entity to RoomResponse DTO ----

    private RoomResponse mapToResponse(Room room) {
        RoomResponse resp = new RoomResponse();
        resp.setId(room.getId());
        resp.setUnit_name(room.getUnitName());
        resp.setMonthly_rate(room.getMonthlyRate());
        resp.setStatus(room.getStatus());
        resp.setFloor(room.getFloor());
        resp.setSize(room.getSize());
        resp.setDescription(room.getDescription());
        resp.setTenant_id(room.getTenantId());
        resp.setTenant_name(room.getTenantName());
        resp.setPhoto_url(room.getPhotoUrl());
        resp.setCreated_at(room.getCreatedAt() != null ? room.getCreatedAt().toString() : null);
        return resp;
    }
}
