package io.khusanjon.onlineclassroom.model.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

/**
 * @author Akbarov Samandar
 * @created 17/06/2021 - 11:05 AM
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private Long id;

    @NotBlank(message = "FullName field is required")
    private String fullName;

    @NotBlank(message = "Phone1 field is required")
    @Pattern(regexp = "^[9][9][8][0-9]{9}$", message = "Phone number must be 12 digits.")
    private String phone1;

//    @NotBlank(message = "Phone2 field is required")
//    @Pattern(regexp = "^[9][9][8][0-9]{9}$", message = "Phone number must be 12 digits.")
    private String phone2;

//    @NotBlank(message = "GuarantorFullName field is required")
    private String guarantorFullName;

//    @NotBlank(message = "GuarantorPhone field is required")
//    @Pattern(regexp = "^[9][9][8][0-9]{9}$", message = "Phone number must be 12 digits.")
    private String guarantorPhone;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean active;
}
