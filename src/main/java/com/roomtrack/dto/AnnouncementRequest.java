package com.roomtrack.dto;

/**
 * DTO for announcement creation requests.
 * Matches Android's AnnouncementRequest.kt
 */
public class AnnouncementRequest {

    private String title;
    private String content;
    private String landlord_id;
    private String landlord_name;

    public AnnouncementRequest() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getLandlord_id() { return landlord_id; }
    public void setLandlord_id(String landlord_id) { this.landlord_id = landlord_id; }

    public String getLandlord_name() { return landlord_name; }
    public void setLandlord_name(String landlord_name) { this.landlord_name = landlord_name; }
}
