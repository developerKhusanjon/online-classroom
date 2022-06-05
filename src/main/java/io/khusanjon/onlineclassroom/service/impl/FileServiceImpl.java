package io.khusanjon.onlineclassroom.service.impl;

import io.khusanjon.onlineclassroom.controller.error.BadRequestAlertException;
import io.khusanjon.onlineclassroom.model.domain.File;
import io.khusanjon.onlineclassroom.model.domain.Staff;
import io.khusanjon.onlineclassroom.model.dto.FileDto;
import io.khusanjon.onlineclassroom.model.dto.FileRequestDto;
import io.khusanjon.onlineclassroom.model.mapper.FileMapper;
import io.khusanjon.onlineclassroom.repository.FileRepository;
import io.khusanjon.onlineclassroom.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Tuychiboev Khusanjon
 * @created 4/27/22 - 4:49 AM
 */
@Service
public class FileServiceImpl implements FileService {

    private final FileLoadService fileLoadService;
    private final FileRepository fileRepository;
    private final FileMapper fileMapper;

    public FileServiceImpl(FileLoadService fileLoadService,
                           FileRepository fileRepository, FileMapper fileMapper) {
        this.fileLoadService = fileLoadService;
        this.fileRepository = fileRepository;
        this.fileMapper = fileMapper;
    }

    @Override
    public List<FileDto> findAll(Long id, String type) throws BadRequestAlertException {
        switch (type) {
            case "SUBJECT" : return fileMapper.toDto(fileRepository.findAllBySubjectId(id));
            case "STAFF" : return fileMapper.toDto(fileRepository.findAllByStaffId(id));
            case "STAFF_CHAIR" : return fileMapper.toDto(fileRepository.findAllByStaffChairId(id));
        }
        throw new BadRequestAlertException("Invalid type", "FileType", "S/S/S", HttpStatus.BAD_REQUEST);
    }

    @Override
    public FileDto findOne(Long id) throws BadRequestAlertException {
        Optional<File> optionalFile = fileRepository.findById(id);
        if (optionalFile.isEmpty())
            throw new BadRequestAlertException(
                    "File not found", "File", "Id", HttpStatus.BAD_REQUEST);

        return fileMapper.toDto(optionalFile.get());
    }

    @Override
    public FileDto save(FileRequestDto fileRequestDto, Staff staff) {
        File file  = fileMapper.toEntity(fileRequestDto);
        file.setStaff(staff);
        file.setLecturer(staff.getFio());
        file.setContentType(fileRequestDto.getFile().getContentType());
        file.setSubjectId(file.getSubjectId());
        file.setStaffChairId(file.getStaffChairId());
        String path = "/files/" + fileLoadService.uploadFile(
                                    fileRequestDto.getFile(), "/files/");
        file.setPath(path);
        return fileMapper.toDto(fileRepository.save(file));
    }

    @Override
    public FileDto update(FileRequestDto fileRequestDto, Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteAll(Long id, String type) {

    }

    @Override
    public Resource download(Long id) throws BadRequestAlertException {
        Optional<File> optionalFile = fileRepository.findById(id);
        if (optionalFile.isEmpty())
            throw new BadRequestAlertException("File not found!", "File", "Id", HttpStatus.BAD_REQUEST);

        return fileLoadService.getFile(optionalFile.get().getPath());
    }
}
