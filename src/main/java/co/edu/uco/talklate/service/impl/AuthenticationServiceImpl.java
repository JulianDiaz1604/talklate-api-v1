package co.edu.uco.talklate.service.impl;

import co.edu.uco.talklate.domain.auth.AuthResponse;
import co.edu.uco.talklate.domain.auth.LoginRequest;
import co.edu.uco.talklate.domain.auth.RegisterRequest;
import co.edu.uco.talklate.domain.user.Role;
import co.edu.uco.talklate.domain.user.User;
import co.edu.uco.talklate.repository.UserRepository;
import co.edu.uco.talklate.repository.entities.UserEntity;
import co.edu.uco.talklate.service.AuthenticationService;
import co.edu.uco.talklate.service.JwtService;
import co.edu.uco.talklate.util.mapper.GenericMapper;
import co.edu.uco.talklate.util.validator.LoginValidator;
import co.edu.uco.talklate.util.validator.NullValidator;
import co.edu.uco.talklate.util.validator.RegisterValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final GenericMapper mapper = new GenericMapper();
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        NullValidator<RegisterRequest> nullValidator = new NullValidator<>();
        RegisterValidator registerValidator = new RegisterValidator(userRepository);
        User user;
        try {
            nullValidator.validateFields(request);
            registerValidator.validate(request);
            user = User.builder()
                    .id(UUID.randomUUID())
                    .name(request.getName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .documentType(request.getDocumentType())
                    .documentNumber(request.getDocumentNumber())
                    .phoneNumber(request.getPhoneNumber())
                    .birthDate(request.getBirthDate())
                    .role(Role.USER)
                    .build();
            userRepository.save(mapper.map(user, UserEntity.class));
            return AuthResponse.builder()
                    .token(jwtService.getToken(user))
                    .username(user.getUsername())
                    .build();
        } catch (IllegalArgumentException e) {
            log.error("Validation error: {}", e.getMessage());
            throw new RuntimeException("Invalid request: " + e.getMessage());
        } catch (Exception e) {
            log.error("An error occurred during registration: {}", e.getMessage(), e);
            throw new RuntimeException("Registration failed due to an internal error.");
        }
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        LoginValidator loginValidator = new LoginValidator(authenticationManager);
        try {
            loginValidator.validate(request.getUsername(), request.getPassword());
            UserDetails user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            String token = jwtService.getToken(user);
            return AuthResponse.builder()
                    .token(token)
                    .username(user.getUsername())
                    .build();
        } catch (IllegalArgumentException e) {
            log.error("Login validation error: {}", e.getMessage());
            throw new RuntimeException("Login failed: " + e.getMessage());
        } catch (Exception e) {
            log.error("An error occurred during login: {}", e.getMessage(), e);
            throw new RuntimeException("Login failed due to an internal error.");
        }
    }

}
