package io.khusanjon.onlineclassroom.repository;

import io.khusanjon.onlineclassroom.model.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author Tuychiboev Khusanjon
 * @created 1/15/22 - 5:46 PM
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByIdAndDeleted(Long id, Boolean deleted);

    Optional<User> findByIdAndDeletedAndActive(Long id, Boolean deleted, Boolean active);

    Page<User> findAllByDeletedOrderByUpdatedAtDesc(Boolean deleted, Pageable pageable);

    List<User> findAllByDeletedAndActive(Boolean deleted, Boolean active);

    List<User> findAllByDeletedAndGuarantorPhoneNotNull(Boolean deleted);

    Page<User> findAllByDeletedAndActiveOrderByUpdatedAtDesc(
            Boolean deleted, Boolean active, Pageable pageable);

    @Modifying
    @Query(nativeQuery = true, value = "update users set deleted = true where id = :id and not deleted")
    void updateDeletedStateById(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select u.*\n" +
            "from users u\n" +
            "where (not u.deleted)\n" +
            "  and (u.full_name ilike concat('%',:search,'%')\n" +
            "    or u.phone1 ilike concat('%',:search,'%')\n" +
            "    or u.phone2 ilike concat('%',:search,'%')\n" +
            "    or u.guarantor_phone ilike concat('%',:search,'%')\n" +
            "    or u.guarantor_full_name ilike concat('%',:search,'%'))\n" +
            "order by updated_at desc")
    Page<User> searchAllBySearchText(@Param("search") String search, Pageable pageable);

    @Query(nativeQuery = true, value = "select  exists(select 1 from users\n" +
            "where (phone1 = :phone) and (id <> :id) and not deleted)")
    boolean existsByPhone(@Param("phone") String phone, @Param("id") Long id);
}
