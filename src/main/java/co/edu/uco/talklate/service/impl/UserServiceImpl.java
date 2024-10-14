package co.edu.uco.talklate.service.impl;

import co.edu.uco.talklate.domain.user.User;
import co.edu.uco.talklate.repository.UserRepository;
import co.edu.uco.talklate.service.UserService;
import co.edu.uco.talklate.util.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GenericMapper mapper;

    @Override
    public User findByUsername(String username) {
        return mapper.map(userRepository.findByUsername(username), User.class);
    }

}
