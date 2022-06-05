package io.khusanjon.onlineclassroom.service;

import io.khusanjon.onlineclassroom.model.domain.Staff;
import io.khusanjon.onlineclassroom.model.dto.StaffSessionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mamadaliyev Nodirbek
 * @created 18/06/2021 - 16:00
 */
public interface StaffSessionService {

    Page<StaffSessionDto> findAll(Pageable pageable, Staff staff);

    void save(HttpServletRequest request, Staff staff, String token);

//    AdminSessionDto findOne(Long id);

}
