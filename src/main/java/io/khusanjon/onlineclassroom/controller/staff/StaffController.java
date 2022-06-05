package io.khusanjon.onlineclassroom.controller.staff;

import io.khusanjon.onlineclassroom.controller.error.BadRequestAlertException;
import io.khusanjon.onlineclassroom.model.dto.AlertResponseDto;
import io.khusanjon.onlineclassroom.model.dto.JWTTokenDto;
import io.khusanjon.onlineclassroom.model.dto.StaffDto;
import io.khusanjon.onlineclassroom.service.StaffService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public ResponseEntity<Page<StaffDto>> getAll(Pageable pageable) {
        Page<StaffDto> result = staffService.findAll(pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffDto> getOne(@PathVariable Long id) throws BadRequestAlertException {
        return ResponseEntity.of(Optional.of(staffService.findOne(id)));
    }

    @Operation(description = "Register user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "if user is created successfully, return JWT and send sms to phone number",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = JWTTokenDto.class))}),
            @ApiResponse(responseCode = "409", description = "required fields",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class),
                            examples = {@ExampleObject(value = "{username:'username is required'...}")})),
            @ApiResponse(responseCode = "400", description = "Other situations throw  BadRequestAlert exception",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestAlertException.class)))})
    @PostMapping
    public ResponseEntity<JWTTokenDto> createAdmin(@RequestBody @Valid StaffDto staffDto) throws Exception {
        if(staffDto.getId() != null){
            throw new BadRequestAlertException("Id is coming", "Admin","adminId");
        }
        return ResponseEntity.ok(staffService.save(staffDto));
    }

    @PutMapping
    public ResponseEntity<JWTTokenDto> updateAdmin(@RequestBody @Valid StaffDto staffDto) throws Exception {
        if(staffDto.getId() == null) {
            throw new BadRequestAlertException("Id is coming null", "Admin", "adminId");
        }
        return ResponseEntity.ok(staffService.save(staffDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AlertResponseDto> deleteAdmin(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(staffService.delete(id));
    }

}
