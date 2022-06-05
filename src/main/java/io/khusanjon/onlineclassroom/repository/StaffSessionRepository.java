package io.khusanjon.onlineclassroom.repository;

import io.khusanjon.onlineclassroom.model.domain.StaffSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Mamadaliyev Nodirbek
 * @created 18/06/2021 - 15:59
 */

public interface StaffSessionRepository extends JpaRepository<StaffSession, Long> {
    Page<StaffSession> findAllByStaffId(Long staff_id, Pageable pageable);
}
