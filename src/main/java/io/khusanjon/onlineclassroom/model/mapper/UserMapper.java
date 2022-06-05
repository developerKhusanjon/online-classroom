package io.khusanjon.onlineclassroom.model.mapper;

import io.khusanjon.onlineclassroom.model.domain.User;
import io.khusanjon.onlineclassroom.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Tuychiboev Khusanjon
 * @created 1/15/22 - 6:15 PM
 */

@Mapper(componentModel = "spring")
@Component
public interface UserMapper extends EntityMapper<UserDto, User> {
}
