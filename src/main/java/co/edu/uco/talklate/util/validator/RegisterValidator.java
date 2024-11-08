package co.edu.uco.talklate.util.validator;

import java.util.regex.Pattern;

import co.edu.uco.talklate.domain.auth.RegisterRequest;
import co.edu.uco.talklate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterValidator {

    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

    private final UserRepository userRepository;

    @Autowired
    public RegisterValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validate(RegisterRequest request) {
        validateEmail(request.getEmail());
        validateUsername(request.getUsername());
        validatePassword(request.getPassword());
    }

    private void validateEmail(String email) {
        if (!Pattern.matches(EMAIL_REGEX, email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email is already registered");
        }
    }

    private void validateUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username is already taken");
        }
    }

    private void validatePassword(String password) {
        if (!Pattern.matches(PASSWORD_REGEX, password)) {
            throw new IllegalArgumentException("Password must be at least 8 characters long, contain at least one number, " +
                    "one uppercase letter, one special character, and no whitespace");
        }
    }

}

