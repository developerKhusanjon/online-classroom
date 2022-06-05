package io.khusanjon.onlineclassroom.model.mapper;

import io.khusanjon.onlineclassroom.model.domain.Role;
import io.khusanjon.onlineclassroom.model.dto.RoleDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author Mamadaliyev Nodirbek
 * @created 18/06/2021 - 16:57
 */
@Mapper(componentModel = "spring")
@Component
public interface RoleMapper {
    Set<Role> toEntity(Set<RoleDto> roleDtos);
}
