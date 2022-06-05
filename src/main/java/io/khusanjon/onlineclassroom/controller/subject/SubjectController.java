package io.khusanjon.onlineclassroom.controller.subject;

import io.khusanjon.onlineclassroom.controller.error.BadRequestAlertException;
import io.khusanjon.onlineclassroom.model.dto.SubjectDto;
import io.khusanjon.onlineclassroom.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tuychiboev Khusanjon
 * @created 4/27/22 - 11:14 AM
 */

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<List<SubjectDto>> getAll(@RequestParam("chairStaff_Id") Long id) {
        return ResponseEntity.ok(subjectService.findAll(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDto> getOne(@PathVariable Long id) throws BadRequestAlertException {
        return ResponseEntity.ok(subjectService.findById(id));
    }

    @PostMapping
    public ResponseEntity<SubjectDto> createSubject(@RequestBody SubjectDto subjectDto) throws BadRequestAlertException {
        if (subjectDto.getId() != null)
            throw new BadRequestAlertException("Id must be null", "Subject", "id", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(subjectService.save(subjectDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectDto> updateSubject(@RequestBody SubjectDto subjectDto) throws BadRequestAlertException {
        if (subjectDto.getId() == null)
            throw new BadRequestAlertException("Id must not be null", "Subject", "id", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(subjectService.save(subjectDto));
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Long id) {
        subjectService.deleteById(id);
    }
}
