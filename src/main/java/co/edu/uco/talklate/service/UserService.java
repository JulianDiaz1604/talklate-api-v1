package co.edu.uco.talklate.service;

import co.edu.uco.talklate.domain.user.User;

public interface UserService {

    User findByUsername(String username);

}
