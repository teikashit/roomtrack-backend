package com.roomtrack.service;

import com.roomtrack.dto.AnnouncementRequest;
import com.roomtrack.dto.AnnouncementResponse;
import com.roomtrack.entity.Announcement;
import com.roomtrack.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for announcement operations.
 */
@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    /**
     * Get all announcements, newest first.
     * Mirrors: GET rest/v1/announcements?order=created_at.desc
     */
    public List<AnnouncementResponse> getAllAnnouncements() {
        return announcementRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Post a new announcement.
     * Mirrors: POST rest/v1/announcements
     * Returns: List<AnnouncementResponse> to match Android's expected response type
     */
    public List<AnnouncementResponse> createAnnouncement(AnnouncementRequest request) {
        Announcement announcement = new Announcement();
        announcement.setTitle(request.getTitle());
        announcement.setContent(request.getContent());
        announcement.setLandlordId(request.getLandlord_id());
        announcement.setLandlordName(request.getLandlord_name());

        Announcement saved = announcementRepository.save(announcement);
        return List.of(mapToResponse(saved));
    }

    /**
     * Delete an announcement by ID.
     * Mirrors: DELETE rest/v1/announcements?id=eq.{id}
     */
    public void deleteAnnouncement(String id) {
        if (!announcementRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Announcement not found");
        }
        announcementRepository.deleteById(id);
    }

    // ---- Helper: map Announcement entity to AnnouncementResponse DTO ----

    private AnnouncementResponse mapToResponse(Announcement announcement) {
        AnnouncementResponse resp = new AnnouncementResponse();
        resp.setId(announcement.getId());
        resp.setTitle(announcement.getTitle());
        resp.setContent(announcement.getContent());
        resp.setLandlord_id(announcement.getLandlordId());
        resp.setLandlord_name(announcement.getLandlordName());
        resp.setCreated_at(announcement.getCreatedAt() != null
                ? announcement.getCreatedAt().toString() : null);
        return resp;
    }
}
