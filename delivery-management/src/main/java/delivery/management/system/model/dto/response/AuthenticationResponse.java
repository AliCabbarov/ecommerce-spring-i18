package delivery.management.system.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import delivery.management.system.model.entity.User;
import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;
}
