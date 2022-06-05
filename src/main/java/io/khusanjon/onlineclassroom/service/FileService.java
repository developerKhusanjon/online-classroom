package io.khusanjon.onlineclassroom.service;

import io.khusanjon.onlineclassroom.controller.error.BadRequestAlertException;
import io.khusanjon.onlineclassroom.model.domain.Staff;
import io.khusanjon.onlineclassroom.model.dto.FileDto;
import io.khusanjon.onlineclassroom.model.dto.FileRequestDto;
import org.springframework.core.io.Resource;

import java.util.List;

/**
 * @author Tuychiboev Khusanjon
 * @created 4/27/22 - 4:11 AM
 */
public interface FileService {
    List<FileDto> findAll(Long id, String type) throws BadRequestAlertException;

    FileDto findOne(Long id) throws BadRequestAlertException;

    FileDto save(FileRequestDto fileRequestDto, Staff staff);

    FileDto update(FileRequestDto fileRequestDto, Long id);

    void deleteById(Long id);

    void deleteAll(Long id, String type);

    Resource download(Long id) throws BadRequestAlertException;
}
