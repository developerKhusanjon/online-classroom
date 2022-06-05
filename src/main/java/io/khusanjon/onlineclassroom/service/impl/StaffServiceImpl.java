package io.khusanjon.onlineclassroom.service.impl;


import io.khusanjon.onlineclassroom.controller.error.BadRequestAlertException;
import io.khusanjon.onlineclassroom.controller.vm.LoginVM;
import io.khusanjon.onlineclassroom.model.domain.Staff;
import io.khusanjon.onlineclassroom.model.domain.Role;
import io.khusanjon.onlineclassroom.model.dto.StaffDto;
import io.khusanjon.onlineclassroom.model.dto.AlertResponseDto;
import io.khusanjon.onlineclassroom.model.dto.JWTTokenDto;
import io.khusanjon.onlineclassroom.model.dto.RoleDto;
import io.khusanjon.onlineclassroom.model.mapper.StaffMapper;
import io.khusanjon.onlineclassroom.repository.StaffRepository;
import io.khusanjon.onlineclassroom.repository.StaffRoleRepository;
import io.khusanjon.onlineclassroom.service.StaffService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author Mamadaliyev Nodirbek
 * @created 19/06/2021 - 10:27
 */
@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final StaffRoleRepository staffRoleRepository;
    private final StaffMapper staffMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthServiceImpl authService;
    private final HttpServletRequest httpServletRequest;

    public StaffServiceImpl(StaffRepository staffRepository,
                            StaffRoleRepository staffRoleRepository,
                            StaffMapper staffMapper,
                            PasswordEncoder passwordEncoder,
                            AuthServiceImpl authService,
                            HttpServletRequest httpServletRequest) {

        this.staffRepository = staffRepository;
        this.staffRoleRepository = staffRoleRepository;
        this.staffMapper = staffMapper;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public Page<StaffDto> findAll(Pageable pageable) {
        return staffRepository.findAll(pageable).map(staffMapper::toDto);
    }

    @Override
    public StaffDto findOne(Long id) throws BadRequestAlertException {
        Optional<Staff> admin = staffRepository.findById(id);
        if (admin.isEmpty())
            throw new BadRequestAlertException("Admin not found", "Admin", "Id", HttpStatus.NOT_FOUND);
        return staffRepository.findById(id).map(staffMapper::toDto).get();
    }

    @Override
    public JWTTokenDto save(StaffDto staffDto) throws Exception {
        Staff staff = staffMapper.toEntity(staffDto);
        boolean existsByUsername = staffRepository.existsByUsername(staffDto.getUsername());
        Set<Role> roles = staffRoleRepository.findDistinctBySetOfIds(
                staffDto.getRoles().stream().map(RoleDto::getId).collect(Collectors.toSet()));

        if (!staffDto.getConfirmPassword().equals(staffDto.getPassword())) {
            throw new BadRequestAlertException("password and confirm password is not match", "Admin", "confirmPassword");
        }

        // create
        if (staffDto.getId() == null) {
            if (existsByUsername) {
                throw new BadRequestAlertException("Username already exits", "Admin", "username");
            }

            staff.setPassword(passwordEncoder.encode(staffDto.getPassword()));
            staff.setRoles(roles);
            staffRepository.save(staff);
            return authService.loginUser(httpServletRequest, new LoginVM(staffDto.getUsername(), staffDto.getPassword()));
        }

        // update
        boolean usernameAndIdNot = staffRepository.existsByUsernameAndIdNot(staffDto.getUsername(), staff.getId());
        if (usernameAndIdNot) {
            throw new BadRequestAlertException("Username already exits", "Admin", "username");
        }

        staff.setPassword(passwordEncoder.encode(staffDto.getPassword()));
        staff.setRoles(roles);
        staffRepository.save(staff);
        return authService.loginUser(httpServletRequest, new LoginVM(staffDto.getUsername(), staffDto.getPassword()));
    }

    @Override
    public AlertResponseDto delete(Long id) throws Exception {
        if (id != null) {
            staffRepository.deleteById(id);
            return new AlertResponseDto("Moderator o'chirildi", true);
        }

        return new AlertResponseDto("Moderator o'chirilmadi", false);
    }
}
