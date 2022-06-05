package io.khusanjon.onlineclassroom.repository;

import io.khusanjon.onlineclassroom.model.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tuychiboev Khusanjon
 * @created 4/29/22 - 3:19 AM
 */
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
