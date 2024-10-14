package co.edu.uco.talklate.service;

import co.edu.uco.talklate.domain.auth.AuthResponse;
import co.edu.uco.talklate.domain.auth.LoginRequest;
import co.edu.uco.talklate.domain.auth.RegisterRequest;
import co.edu.uco.talklate.domain.user.User;

public interface AuthenticationService {

    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);

}
