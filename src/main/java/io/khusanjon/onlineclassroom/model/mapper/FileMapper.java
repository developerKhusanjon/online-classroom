package io.khusanjon.onlineclassroom.model.mapper;

import io.khusanjon.onlineclassroom.model.domain.File;
import io.khusanjon.onlineclassroom.model.dto.FileDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Tuychiboev Khusanjon
 * @created 4/27/22 - 4:18 AM
 */

@Mapper(componentModel = "spring")
@Component
public interface FileMapper extends EntityMapper<FileDto, File> {
}
