package com.roomtrack.repository;

import com.roomtrack.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Announcement entity.
 */
@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, String> {

    List<Announcement> findAllByOrderByCreatedAtDesc();
}
