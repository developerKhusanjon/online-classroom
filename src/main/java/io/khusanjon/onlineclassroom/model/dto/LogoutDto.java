package io.khusanjon.onlineclassroom.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Akbarov Samandar
 * @created 27/08/2021 - 6:14 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogoutDto {
    private String deviceToken;
}
