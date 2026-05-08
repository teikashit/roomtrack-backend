package com.roomtrack.repository;

import com.roomtrack.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Room entity.
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, String> {

    List<Room> findAllByOrderByUnitNameAsc();

    Optional<Room> findByTenantId(String tenantId);
}
