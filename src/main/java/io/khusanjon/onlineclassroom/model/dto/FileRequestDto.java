package io.khusanjon.onlineclassroom.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Tuychiboev Khusanjon
 * @created 4/29/22 - 1:26 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FileRequestDto extends FileDto{
    private MultipartFile file;
}
