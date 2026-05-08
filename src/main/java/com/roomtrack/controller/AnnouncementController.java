package com.roomtrack.controller;

import com.roomtrack.dto.AnnouncementRequest;
import com.roomtrack.dto.AnnouncementResponse;
import com.roomtrack.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for announcement endpoints.
 *
 * GET    /announcements      — Get all announcements (both roles)
 * POST   /announcements      — Post an announcement (landlord)
 * DELETE /announcements/{id} — Delete an announcement (landlord)
 *
 * All endpoints require a valid Bearer JWT token.
 */
@RestController
@RequestMapping("/announcements")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    /**
     * Get all announcements, newest first.
     * Android call: GET /announcements
     */
    @GetMapping
    public ResponseEntity<List<AnnouncementResponse>> getAllAnnouncements() {
        return ResponseEntity.ok(announcementService.getAllAnnouncements());
    }

    /**
     * Post a new announcement.
     * Returns List<AnnouncementResponse> to match Android's expected response type.
     * Android call: POST /announcements
     */
    @PostMapping
    public ResponseEntity<List<AnnouncementResponse>> createAnnouncement(
            @RequestBody AnnouncementRequest request) {
        return ResponseEntity.ok(announcementService.createAnnouncement(request));
    }

    /**
     * Delete an announcement by ID.
     * Android call: DELETE /announcements/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable String id) {
        announcementService.deleteAnnouncement(id);
        return ResponseEntity.ok().build();
    }
}
