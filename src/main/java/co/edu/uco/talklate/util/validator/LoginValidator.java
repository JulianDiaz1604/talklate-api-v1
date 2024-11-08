package co.edu.uco.talklate.util.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class LoginValidator {

    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginValidator(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void validate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (AuthenticationException e) {
            throw new IllegalArgumentException("Invalid username or password");
        }
    }
}

