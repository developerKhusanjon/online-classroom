package io.khusanjon.onlineclassroom.repository;

import io.khusanjon.onlineclassroom.model.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * @author Tuychiboev Khusanjon
 * @created 11/14/21 - 1:27 PM
 */
public interface StaffRoleRepository extends JpaRepository<Role, Long> {
    @Query(nativeQuery = true, value = "select r.* from role r where r.id in (:ids)")
    Set<Role> findDistinctBySetOfIds(@Param("ids") Set<Long> ids);
}
