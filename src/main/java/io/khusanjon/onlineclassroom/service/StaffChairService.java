package io.khusanjon.onlineclassroom.service;

import io.khusanjon.onlineclassroom.controller.error.BadRequestAlertException;
import io.khusanjon.onlineclassroom.model.dto.StaffChairDto;

import java.util.List;

/**
 * @author Tuychiboev Khusanjon
 * @created 4/27/22 - 5:05 AM
 */
public interface StaffChairService {

    List<StaffChairDto> findAll();

    StaffChairDto findById(Long id) throws BadRequestAlertException;

    StaffChairDto save(StaffChairDto staffChairDto);

    void deleteById(Long id);
}
