package io.khusanjon.onlineclassroom.service;

import io.khusanjon.onlineclassroom.controller.error.BadRequestAlertException;
import io.khusanjon.onlineclassroom.model.dto.StaffDto;
import io.khusanjon.onlineclassroom.model.dto.AlertResponseDto;
import io.khusanjon.onlineclassroom.model.dto.JWTTokenDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Mamadaliyev Nodirbek
 * @created 19/06/2021 - 10:22
 */
public interface StaffService {

    Page<StaffDto> findAll(Pageable pageable);

    StaffDto findOne(Long id) throws BadRequestAlertException;

    JWTTokenDto save(StaffDto userDto) throws Exception;

    AlertResponseDto delete(Long id) throws Exception;
}
