package io.khusanjon.onlineclassroom.service;

import io.khusanjon.onlineclassroom.controller.error.BadRequestAlertException;
import io.khusanjon.onlineclassroom.model.dto.SubjectDto;

import java.util.List;

/**
 * @author Tuychiboev Khusanjon
 * @created 4/27/22 - 4:52 AM
 */
public interface SubjectService {

    List<SubjectDto> findAll(Long id);

    SubjectDto findById(Long id) throws BadRequestAlertException;

    SubjectDto save(SubjectDto subjectDto);

    void deleteById(Long id);
}
