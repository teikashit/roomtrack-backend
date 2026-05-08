package com.roomtrack.dto;

/**
 * DTO for assigning/unassigning a tenant to/from a room.
 * Matches Android's AssignTenantRequest.kt
 */
public class AssignTenantRequest {

    private String tenant_id;
    private String tenant_name;
    private String status;

    public AssignTenantRequest() {}

    public String getTenant_id() { return tenant_id; }
    public void setTenant_id(String tenant_id) { this.tenant_id = tenant_id; }

    public String getTenant_name() { return tenant_name; }
    public void setTenant_name(String tenant_name) { this.tenant_name = tenant_name; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
