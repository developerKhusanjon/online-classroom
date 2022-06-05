package io.khusanjon.onlineclassroom.model.dto;

import lombok.*;

/**
 * @author Tuychiboev Khusanjon
 * @created 4/27/22 - 4:17 AM
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDto {
    private Long id;

    private String topic;

    private String lecturer;

    private String url;

    private String contentType;

    private Long subjectId;

    private Long staffChairId;
}
