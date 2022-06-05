package io.khusanjon.onlineclassroom.model.dto;

import lombok.*;

/**
 * @author Tuychiboev Khusanjon
 * @created 4/27/22 - 4:55 AM
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectDto {
    private Long id;
    private String name;
}
