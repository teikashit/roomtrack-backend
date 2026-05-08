-- =============================================================
-- RoomTrack — Supabase PostgreSQL Migration Script
-- Run this in your Supabase SQL Editor before starting Spring Boot
-- =============================================================
--
-- PURPOSE:
--   The existing "profiles" table was created by Supabase Auth and
--   does not have email or password_hash columns. Spring Boot needs
--   these to authenticate users independently of Supabase Auth.
--
-- WHAT THIS SCRIPT DOES:
--   1. Adds email and password_hash columns to the profiles table
--   2. Creates an index on email for fast lookups
--   3. Seeds a test landlord and test tenant account
--      (CHANGE the hashed passwords before going to production!)
-- =============================================================

-- Step 1: Add email column (unique, required for login)
ALTER TABLE profiles
    ADD COLUMN IF NOT EXISTS email VARCHAR(255) UNIQUE,
    ADD COLUMN IF NOT EXISTS password_hash VARCHAR(255);

-- Step 2: Index on email for fast login lookups
CREATE INDEX IF NOT EXISTS idx_profiles_email ON profiles(email);
CREATE INDEX IF NOT EXISTS idx_profiles_role  ON profiles(role);

-- =============================================================
-- Step 3: Seed test accounts
--
-- Passwords below are BCrypt hashes. Plaintext values:
--   Landlord password : landlord123
--   Tenant password   : tenant123
--
-- Generate your own hashes at: https://bcrypt-generator.com
-- or run: new BCryptPasswordEncoder().encode("yourpassword")
-- =============================================================

-- Test landlord account
INSERT INTO profiles (id, email, password_hash, full_name, phone, role)
VALUES (
    gen_random_uuid()::text,
    'landlord@roomtrack.com',
    '$2a$10$N.sH4yj9Sg3C1gd7YgqzLOnIzrkgmXJbqe1r3W/.9JwN.J9GQXZ.e',
    'Test Landlord',
    '09171234567',
    'landlord'
)
ON CONFLICT (email) DO NOTHING;

-- Test tenant account
INSERT INTO profiles (id, email, password_hash, full_name, phone, role)
VALUES (
    gen_random_uuid()::text,
    'tenant@roomtrack.com',
    '$2a$10$Dq5oFmO5f0b3CJEg7GKhK.qMy2zBfrOvzBk.EFRx7fjDvCxvYj/dO',
    'Test Tenant',
    '09187654321',
    'tenant'
)
ON CONFLICT (email) DO NOTHING;

-- =============================================================
-- Verify the table structure after migration
-- =============================================================
SELECT column_name, data_type, is_nullable
FROM information_schema.columns
WHERE table_name = 'profiles'
ORDER BY ordinal_position;
