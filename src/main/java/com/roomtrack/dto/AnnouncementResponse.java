package com.roomtrack.dto;

/**
 * DTO for announcement responses.
 * Matches Android's AnnouncementResponse.kt
 */
public class AnnouncementResponse {

    private String id;
    private String title;
    private String content;
    private String landlord_id;
    private String landlord_name;
    private String created_at;

    public AnnouncementResponse() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getLandlord_id() { return landlord_id; }
    public void setLandlord_id(String landlord_id) { this.landlord_id = landlord_id; }

    public String getLandlord_name() { return landlord_name; }
    public void setLandlord_name(String landlord_name) { this.landlord_name = landlord_name; }

    public String getCreated_at() { return created_at; }
    public void setCreated_at(String created_at) { this.created_at = created_at; }
}
