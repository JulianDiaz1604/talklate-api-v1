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
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final GenericMapper mapper = new GenericMapper();
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
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
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

}
