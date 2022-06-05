package io.khusanjon.onlineclassroom.components;

import io.khusanjon.onlineclassroom.model.domain.Staff;
import io.khusanjon.onlineclassroom.model.domain.Role;
import io.khusanjon.onlineclassroom.model.domain.enumaration.RoleName;
import io.khusanjon.onlineclassroom.repository.StaffRepository;
import io.khusanjon.onlineclassroom.repository.StaffRoleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/**
 * @author Mamadaliyev Nodirbek
 * @created 18/06/2021 - 17:43
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final StaffRepository staffRepository;
    private final PasswordEncoder passwordEncoder;
    private final StaffRoleRepository roleRepository;

    public DataLoader(StaffRepository adminsSessionsRepository,
                      PasswordEncoder passwordEncoder, StaffRoleRepository roleRepository) {
        this.staffRepository = adminsSessionsRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String modeInitial;


    @Override
    public void run(String... args) throws Exception {

        if(modeInitial.equals("create")) {
            Role.RoleBuilder roleBuilder = Role.builder().roleName(RoleName.ADMIN);
            roleRepository.save(roleBuilder.build());
            roleBuilder = Role.builder().roleName(RoleName.LECTURER);
            roleRepository.save(roleBuilder.build());


            HashSet<Role> roles = new HashSet<>(roleRepository.findAll());

            Staff admins = new Staff();
            admins.setFio("Firdavs");
            admins.setUsername("Firdavs");
            admins.setPassword(passwordEncoder.encode("f5616"));
            admins.setPhone("998934795616");
            admins.setRoles(roles);
            staffRepository.save(admins);
        }
    }
}
