package orz.joey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import orz.joey.repository.UserRepository;
import orz.joey.repository.entity.User;
import orz.joey.service.UserService;
import orz.joey.service.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto findById(Long userId) {
        UserDto userDto = new UserDto();
        Optional<User> user = userRepository.findById(userId);
        //po -> dto
        user.ifPresent(u -> {
            userDto.setId(u.getId());
            userDto.setUsername(u.getUsername());
            userDto.setCellphone(u.getCellphone());
            userDto.setEmail(u.getEmail());
        });
        return userDto;
    }
}
