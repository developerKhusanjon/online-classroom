package io.khusanjon.onlineclassroom.controller.staff;

import io.khusanjon.onlineclassroom.controller.error.BadRequestAlertException;
import io.khusanjon.onlineclassroom.model.dto.StaffChairDto;
import io.khusanjon.onlineclassroom.service.StaffChairService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tuychiboev Khusanjon
 * @created 4/29/22 - 12:48 AM
 */

@RestController
@RequestMapping("/api/staff-chair")
public class StaffChairController {

    private final StaffChairService staffChairService;

    public StaffChairController(StaffChairService staffChairService) {
        this.staffChairService = staffChairService;
    }

    @GetMapping
    public ResponseEntity<List<StaffChairDto>> getAll() {
        return ResponseEntity.ok(staffChairService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffChairDto> getOne(@PathVariable Long id) throws BadRequestAlertException {
        return ResponseEntity.ok(staffChairService.findById(id));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<StaffChairDto> createStaffChair(@RequestBody StaffChairDto staffChairDto) throws BadRequestAlertException {
        if (staffChairDto.getId() != null)
            throw new BadRequestAlertException("Id must be null", "StaffChair", "id", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(staffChairService.save(staffChairDto));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<StaffChairDto> updateStaffChair(@RequestBody StaffChairDto staffChairDto) throws BadRequestAlertException {
        if (staffChairDto.getId() == null)
            throw new BadRequestAlertException("Id must not be null", "StaffChair", "id", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(staffChairService.save(staffChairDto));
    }

    @DeleteMapping("/{id}")
    public void deleteStaffChair(@PathVariable Long id) {
        staffChairService.deleteById(id);
    }
}
