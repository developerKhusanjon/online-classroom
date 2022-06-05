package io.khusanjon.onlineclassroom.security;

import io.khusanjon.onlineclassroom.model.domain.Staff;
import io.khusanjon.onlineclassroom.repository.StaffRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final StaffRepository staffRepository;

    public DomainUserDetailsService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    @Transactional
    public Staff loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);

        return staffRepository.findByUsername(login)
                .map(user -> createSpringSecurityUser(login, user))
                .orElseThrow(() -> new UserNotFoundException("User with username " + login + " was not found in the database"));

    }

    public Staff loadAdminsId(Long id) {
        return staffRepository.findById(id)
                .map(user -> createSpringSecurityUser(String.valueOf(id), user))
                .orElseThrow(() -> new UsernameNotFoundException("User with userId " + id + " was not found in the database"));
    }

    private Staff createSpringSecurityUser(String login, Staff user) {
        if (!user.isEnabled()) {
            throw new UserNotFoundException("User " + login + " was not activated");
        }

        return user;
    }
}
