package io.khusanjon.onlineclassroom.repository;

import io.khusanjon.onlineclassroom.model.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Tuychiboev Khusanjon
 * @created 4/24/22 - 5:05 PM
 */
public interface FileRepository extends JpaRepository<File, Long> {

    List<File> findAllBySubjectId(Long subject_id);

    List<File> findAllByStaffId(Long staff_id);

    List<File> findAllByStaffChairId(Long staffChair_id);
}
