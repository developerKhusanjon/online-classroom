package io.khusanjon.onlineclassroom.controller.staff;

import io.khusanjon.onlineclassroom.model.domain.Staff;
import io.khusanjon.onlineclassroom.model.dto.StaffSessionDto;
import io.khusanjon.onlineclassroom.security.CurrentUser;
import io.khusanjon.onlineclassroom.service.StaffSessionService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mamadaliyev Nodirbek
 * @created 19/06/2021 - 23:50
 */

@RestController
@RequestMapping("/api/staff-session")
public class StaffSessionController {

    private final StaffSessionService staffSessionService;

    public StaffSessionController(StaffSessionService staffSessionService) {
        this.staffSessionService = staffSessionService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<Page<StaffSessionDto>> getAllAdminSession(Pageable pageable, @Parameter(hidden = true) @CurrentUser Staff staff) {
        Page<StaffSessionDto> result = staffSessionService.findAll(pageable, staff);
        return ResponseEntity.ok(result);
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<AdminSessionDto> getAllAdminSession(@PathVariable Long id){
//        AdminSessionDto adminSessionDtos = adminSessionService.findOne(id);
//        return ResponseEntity.ok(adminSessionDtos);
//    }
}
