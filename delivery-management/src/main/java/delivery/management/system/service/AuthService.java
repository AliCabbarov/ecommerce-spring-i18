package delivery.management.system.service;

import delivery.management.system.model.dto.request.AuthRequestDto;
import delivery.management.system.model.dto.response.AuthenticationResponse;
import delivery.management.system.model.entity.User;

public interface AuthService {


    AuthenticationResponse authentication(AuthRequestDto dto);
    AuthenticationResponse accessTokensByRefreshToken(String refreshToken);
    public User getAuthenticatedUser();
}
