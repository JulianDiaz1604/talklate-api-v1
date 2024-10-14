package co.edu.uco.talklate.domain.auth;

import co.edu.uco.talklate.domain.user.Role;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    String name;
    String lastName;
    String email;
    String username;
    String password;
    String documentType;
    String documentNumber;
    String phoneNumber;
    Date birthDate;
    Role role;

}
