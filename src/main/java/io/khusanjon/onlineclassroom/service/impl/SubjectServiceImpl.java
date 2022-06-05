package io.khusanjon.onlineclassroom.service.impl;

import io.khusanjon.onlineclassroom.controller.error.BadRequestAlertException;
import io.khusanjon.onlineclassroom.model.domain.Subject;
import io.khusanjon.onlineclassroom.model.dto.SubjectDto;
import io.khusanjon.onlineclassroom.model.mapper.SubjectMapper;
import io.khusanjon.onlineclassroom.repository.SubjectRepository;
import io.khusanjon.onlineclassroom.service.SubjectService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Tuychiboev Khusanjon
 * @created 4/27/22 - 5:05 AM
 */

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public SubjectServiceImpl(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    @Override
    public List<SubjectDto> findAll(Long id) {
        return subjectMapper.toDto(subjectRepository.findAll(Pageable.unpaged()).getContent());
    }

    @Override
    public SubjectDto findById(Long id) throws BadRequestAlertException {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isEmpty())
            throw new BadRequestAlertException("Subject not found", "Subject", "Id", HttpStatus.BAD_REQUEST);
        return subjectMapper.toDto(optionalSubject.get());
    }

    @Override
    public SubjectDto save(SubjectDto subjectDto) {
        return subjectMapper.toDto(subjectRepository.save(subjectMapper.toEntity(subjectDto)));
    }

    @Override
    public void deleteById(Long id) {
        subjectRepository.deleteById(id);
    }
}
