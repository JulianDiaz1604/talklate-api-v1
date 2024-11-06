package co.edu.uco.talklate.controller;

import co.edu.uco.talklate.domain.auth.AuthResponse;
import co.edu.uco.talklate.domain.auth.LoginRequest;
import co.edu.uco.talklate.domain.auth.RegisterRequest;
import co.edu.uco.talklate.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(value = "register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            AuthResponse authResponse = authenticationService.register(request);
            if (authResponse != null) {
                return ResponseEntity.ok(authResponse);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration couldn't be completed");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping(value = "login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            AuthResponse authResponse = authenticationService.login(request);
            if (authResponse != null) {
                return ResponseEntity.ok(authResponse);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't login. Please check fields and try again");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
