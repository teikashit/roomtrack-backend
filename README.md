# RoomTrack Spring Boot Backend — Setup Guide

## Overview

This is the Java Spring Boot backend for RoomTrack.  
It replaces Supabase's REST API while keeping the **same Supabase PostgreSQL database**.

```
Android App  →  Spring Boot (port 8080)  →  Supabase PostgreSQL
                                         ↘  Supabase Storage (photos only)
```

---

## Prerequisites

| Tool | Version |
|------|---------|
| Java JDK | 17 or higher |
| Maven | 3.8+ |
| Supabase project | existing (keep as-is) |

---

## Step 1 — Run the Database Migration

Before starting Spring Boot, run `src/main/resources/migration.sql` in your **Supabase SQL Editor**.

This adds two new columns to the `profiles` table:
- `email` — used for login
- `password_hash` — BCrypt-hashed password stored by Spring Boot

> **Important:** All new users registered through Spring Boot will get their
> password stored here. Existing Supabase Auth users will need to re-register
> through the app.

---

## Step 2 — Configure `application.properties`

Edit `src/main/resources/application.properties`:

```properties
# Your Supabase PostgreSQL password (from Supabase → Settings → Database)
spring.datasource.password=YOUR_SUPABASE_DB_PASSWORD

# Replace with a long random Base64-encoded secret (≥256 bits)
# Generate one: openssl rand -base64 32
jwt.secret=ZW50ZXJBTmV3UmFuZG9tU2VjcmV0S2V5SGVyZVRoYXRJc0F0TGVhc3QyNTZCaXRzTG9uZw==
```

### Finding your Supabase DB password:
1. Go to [supabase.com](https://supabase.com) → your project
2. Settings → Database → Connection string
3. Copy the password from the URI

---

## Step 3 — Build and Run

```bash
cd roomtrack-backend
mvn clean install
mvn spring-boot:run
```

The server starts on **http://localhost:8080**

---

## Step 4 — Configure Android

In `RetrofitClient.kt`, set the correct BASE_URL:

| Scenario | URL |
|----------|-----|
| Android Emulator | `http://10.0.2.2:8080/` |
| Physical device (same Wi-Fi) | `http://192.168.1.X:8080/` |
| Production server | `http://your-server.com:8080/` |

Also add this to `AndroidManifest.xml` to allow HTTP traffic (for development):
```xml
<application
    android:usesCleartextTraffic="true"
    ...>
```

---

## API Reference

### Auth (Public — no token required)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/auth/login` | Login, returns JWT token |
| POST | `/auth/register` | Register new user |

### Profiles (Bearer token required)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/profiles/{id}` | Get profile by user ID |
| GET | `/profiles/tenants` | Get all tenants |
| POST | `/profiles` | Create/update profile |
| PUT | `/profiles/password` | Change password |

### Rooms (Bearer token required)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/rooms` | Get all rooms |
| GET | `/rooms/tenant/{tenantId}` | Get room by tenant |
| POST | `/rooms` | Create room |
| PATCH | `/rooms/{id}/assign` | Assign tenant to room |
| PATCH | `/rooms/{id}/unassign` | Remove tenant from room |

### Payments (Bearer token required)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/payments` | Get all payments |
| GET | `/payments/tenant/{tenantId}` | Get payments for tenant |
| POST | `/payments` | Create payment |
| PATCH | `/payments/{id}/status` | Update status (Paid / For Verification / Pending) |

### Announcements (Bearer token required)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/announcements` | Get all announcements |
| POST | `/announcements` | Create announcement |
| DELETE | `/announcements/{id}` | Delete announcement |

---

## Test Accounts (from migration.sql)

| Role | Email | Password |
|------|-------|----------|
| Landlord | landlord@roomtrack.com | landlord123 |
| Tenant | tenant@roomtrack.com | tenant123 |

---

## Project Structure

```
roomtrack-backend/
├── src/main/java/com/roomtrack/
│   ├── RoomTrackApplication.java       ← Entry point
│   ├── config/
│   │   └── SecurityConfig.java         ← Spring Security + CORS + BCrypt
│   ├── controller/
│   │   ├── AuthController.java         ← POST /auth/login, /auth/register
│   │   ├── ProfileController.java      ← GET/POST /profiles, PUT /profiles/password
│   │   ├── RoomController.java         ← GET/POST/PATCH /rooms
│   │   ├── PaymentController.java      ← GET/POST/PATCH /payments
│   │   └── AnnouncementController.java ← GET/POST/DELETE /announcements
│   ├── dto/                            ← Request/Response data classes (mirrors Android models)
│   ├── entity/                         ← JPA entities (map to Supabase PostgreSQL tables)
│   ├── repository/                     ← Spring Data JPA repositories
│   ├── security/
│   │   ├── JwtUtil.java                ← JWT generation and validation
│   │   └── JwtAuthFilter.java          ← Extracts Bearer token on every request
│   └── service/
│       ├── AuthService.java            ← Login + register logic
│       ├── ProfileService.java         ← Profile CRUD
│       ├── RoomService.java            ← Room CRUD
│       ├── PaymentService.java         ← Payment CRUD
│       └── AnnouncementService.java    ← Announcement CRUD
├── src/main/resources/
│   ├── application.properties          ← DB connection + JWT config
│   └── migration.sql                   ← Run once in Supabase SQL Editor
└── pom.xml                             ← Maven dependencies
```

---

## Android Files Changed

| File | Change |
|------|--------|
| `api/RetrofitClient.kt` | BASE_URL → Spring Boot; removed `apikey` header; added auto-JWT injection |
| `api/AuthApiService.kt` | `/auth/login`, `/auth/register` |
| `api/ProfileApiService.kt` | `/profiles/{id}`, `/profiles/tenants`, `/profiles`, `/profiles/password` |
| `api/RoomApiService.kt` | `/rooms`, `/rooms/tenant/{id}`, `/rooms/{id}/assign`, `/rooms/{id}/unassign` |
| `api/PaymentApiService.kt` | `/payments`, `/payments/tenant/{id}`, `/payments/{id}/status` |
| `api/AnnouncementApiService.kt` | `/announcements`, `/announcements/{id}` |
| `screens/dashboard/DashboardModel.kt` | Removed `eq.` params; returns single ProfileResponse |
| `screens/rooms/RoomsModel.kt` | Removed `eq.` params; separate unassign endpoint |
| `screens/payments/PaymentModel.kt` | Removed `eq.` params; unified status update |
| `screens/announcements/AnnouncementModel.kt` | Removed `eq.` params |
| `screens/profile/ProfileModel.kt` | Single-object getProfile; removed updateMetadata |
| `screens/profile/ProfilePresenter.kt` | Handles single ProfileResponse; removed updateMetadata call |
