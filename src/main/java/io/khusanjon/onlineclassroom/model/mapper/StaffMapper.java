package io.khusanjon.onlineclassroom.model.mapper;

import io.khusanjon.onlineclassroom.model.domain.Staff;
import io.khusanjon.onlineclassroom.model.dto.StaffDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring", uses = {RoleMapper.class})
@Component
public interface StaffMapper extends EntityMapper<StaffDto, Staff> {

}
