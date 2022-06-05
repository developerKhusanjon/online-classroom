package io.khusanjon.onlineclassroom.model.mapper;

import io.khusanjon.onlineclassroom.model.domain.StaffChair;
import io.khusanjon.onlineclassroom.model.dto.StaffChairDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Tuychiboev Khusanjon
 * @created 5/11/22 - 8:49 AM
 */

@Mapper(componentModel = "spring")
@Component
public interface StaffChairMapper extends EntityMapper<StaffChairDto, StaffChair> {
}
