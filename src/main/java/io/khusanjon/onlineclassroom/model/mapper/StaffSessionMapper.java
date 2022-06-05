package io.khusanjon.onlineclassroom.model.mapper;

import io.khusanjon.onlineclassroom.model.domain.StaffSession;
import io.khusanjon.onlineclassroom.model.dto.StaffSessionDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Mamadaliyev Nodirbek
 * @created 19/06/2021 - 12:52
 */

@Mapper(componentModel = "spring", uses = {StaffMapper.class})
@Component
public interface StaffSessionMapper extends EntityMapper<StaffSessionDto, StaffSession> {
}
