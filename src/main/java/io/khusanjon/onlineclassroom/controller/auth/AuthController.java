package io.khusanjon.onlineclassroom.controller.auth;

import io.khusanjon.onlineclassroom.controller.error.BadRequestAlertException;
import io.khusanjon.onlineclassroom.controller.vm.LoginVM;
import io.khusanjon.onlineclassroom.model.dto.JWTTokenDto;
import io.khusanjon.onlineclassroom.security.jwt.JwtAuthenticationFilter;
import io.khusanjon.onlineclassroom.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(description = "Login user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "if user is entered to BabyApp successfully, return JWT",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = JWTTokenDto.class))}),
            @ApiResponse(responseCode = "409", description = "required fields",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class),
                            examples = {@ExampleObject(value = "{username:'username is required'...}")})),
            @ApiResponse(responseCode = "400", description = "Other situations throw  BadRequestAlert exception",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestAlertException.class)))})
    @PostMapping("/login")
    public ResponseEntity<JWTTokenDto> login(HttpServletRequest request, @RequestBody @Valid LoginVM loginVM) {
        JWTTokenDto jwt = authService.loginUser(request, loginVM);
        if (jwt.isSuccess()) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JwtAuthenticationFilter.AUTHORIZATION_HEADER, "Bearer " + jwt.getToken());
            return new ResponseEntity<>(jwt, httpHeaders, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body(jwt);

    }

}
