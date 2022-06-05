package io.khusanjon.onlineclassroom.model.mapper;

import io.khusanjon.onlineclassroom.model.domain.Subject;
import io.khusanjon.onlineclassroom.model.dto.SubjectDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Tuychiboev Khusanjon
 * @created 4/29/22 - 4:55 AM
 */

@Mapper(componentModel = "spring")
@Component
public interface SubjectMapper extends EntityMapper<SubjectDto, Subject> {
}
