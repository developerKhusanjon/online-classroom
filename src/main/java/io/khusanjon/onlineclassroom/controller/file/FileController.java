package io.khusanjon.onlineclassroom.controller.file;

import io.khusanjon.onlineclassroom.controller.error.BadRequestAlertException;
import io.khusanjon.onlineclassroom.model.domain.Staff;
import io.khusanjon.onlineclassroom.model.dto.FileDto;
import io.khusanjon.onlineclassroom.model.dto.FileRequestDto;
import io.khusanjon.onlineclassroom.security.CurrentUser;
import io.khusanjon.onlineclassroom.service.FileService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author Tuychiboev Khusanjon
 * @created 4/29/22 - 1:01 AM
 */

@RestController
@RequestMapping("/api/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public ResponseEntity<List<FileDto>> getAll(@RequestParam("secId") Long id, @RequestParam("type") String type) throws BadRequestAlertException {
        return ResponseEntity.ok(fileService.findAll(id, type));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileDto> getOne(@PathVariable Long id) throws BadRequestAlertException {
        return ResponseEntity.ok(fileService.findOne(id));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) throws IOException, BadRequestAlertException {
        return ResponseEntity.ok(fileService.download(id).getInputStream().readAllBytes());
    }

    @PostMapping("/upload")
    public ResponseEntity<FileDto> uploadFile(@RequestBody FileRequestDto fileRequestDto, @CurrentUser Staff staff) {
        return ResponseEntity.ok(fileService.save(fileRequestDto, staff));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FileDto> uploadFile(@RequestBody FileRequestDto fileRequestDto, @PathVariable Long id) {
        return ResponseEntity.ok(fileService.update(fileRequestDto, id));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/all/{id}")
    public void deleteAll(@PathVariable Long id, @RequestParam("type") String type) {
        fileService.deleteAll(id, type);
    }

    @DeleteMapping("/{id}")
    public void deleteFile(@PathVariable Long id) {
        fileService.deleteById(id);
    }
}
