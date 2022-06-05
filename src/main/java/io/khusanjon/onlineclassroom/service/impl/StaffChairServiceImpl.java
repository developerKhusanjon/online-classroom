package io.khusanjon.onlineclassroom.service.impl;

import io.khusanjon.onlineclassroom.controller.error.BadRequestAlertException;
import io.khusanjon.onlineclassroom.model.domain.StaffChair;
import io.khusanjon.onlineclassroom.model.dto.StaffChairDto;
import io.khusanjon.onlineclassroom.model.mapper.StaffChairMapper;
import io.khusanjon.onlineclassroom.repository.StaffChairRepository;
import io.khusanjon.onlineclassroom.service.StaffChairService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Tuychiboev Khusanjon
 * @created 4/27/22 - 5:07 AM
 */

@Service
public class StaffChairServiceImpl implements StaffChairService {

    private final StaffChairRepository staffChairRepository;
    private final StaffChairMapper staffChairMapper;

    public StaffChairServiceImpl(StaffChairRepository staffChairRepository, StaffChairMapper staffChairMapper) {
        this.staffChairRepository = staffChairRepository;
        this.staffChairMapper = staffChairMapper;
    }

    @Override
    public List<StaffChairDto> findAll() {
        return staffChairMapper.toDto(staffChairRepository.findAll());
    }

    @Override
    public StaffChairDto findById(Long id) throws BadRequestAlertException {
        Optional<StaffChair> staffChair = staffChairRepository.findById(id);
        if (staffChair.isEmpty())
            throw new BadRequestAlertException("StaffChair not found", "StaffChair", "Id", HttpStatus.BAD_REQUEST);
        return staffChairMapper.toDto(staffChair.get());
    }

    @Override
    public StaffChairDto save(StaffChairDto staffChairDto) {
        return staffChairMapper.toDto(staffChairRepository.save(staffChairMapper.toEntity(staffChairDto)));
    }

    @Override
    public void deleteById(Long id) {
        staffChairRepository.deleteById(id);
    }
}
