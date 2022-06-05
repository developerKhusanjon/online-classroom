package io.khusanjon.onlineclassroom.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Mamadaliyev Nodirbek
 * @created 18/06/2021 - 15:10
 */
@Getter
@Setter
@AllArgsConstructor
public class JWTTokenDto {
    private String token;
    private boolean success;

    public JWTTokenDto(String token) {
        this.token = token;
    }

    @JsonProperty("id_token")
    public String getToken() {
        return token;
    }

    public void setIdToken(String token) {
        this.token = token;
    }

}
