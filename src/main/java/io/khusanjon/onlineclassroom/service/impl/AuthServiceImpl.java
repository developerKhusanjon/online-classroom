package io.khusanjon.onlineclassroom.service.impl;

import io.khusanjon.onlineclassroom.controller.vm.LoginVM;
import io.khusanjon.onlineclassroom.model.domain.Staff;
import io.khusanjon.onlineclassroom.model.dto.JWTTokenDto;
import io.khusanjon.onlineclassroom.security.jwt.JwtTokenProvider;
import io.khusanjon.onlineclassroom.service.StaffSessionService;
import io.khusanjon.onlineclassroom.service.AuthService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 * @author Mamadaliyev Nodirbek
 * @created 18/06/2021 - 15:14
 */
@Service
public class AuthServiceImpl implements AuthService {
    private final JwtTokenProvider tokenProvider;
    private final StaffSessionService staffSessionService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthServiceImpl(JwtTokenProvider tokenProvider, StaffSessionService staffSessionService,
                           AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.staffSessionService = staffSessionService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }


    @Override
    public JWTTokenDto loginUser(HttpServletRequest request, @NotNull LoginVM loginVM) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());
        Authentication authentication = null;
        try {
            authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken(authentication);
            staffSessionService.save(request, (Staff) authentication.getPrincipal(), jwt);
            return new JWTTokenDto(jwt, true);
        } catch (Exception e) {
            return (new JWTTokenDto("Username or Password invalid", false));
        }
    }


}
