package orz.joey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import orz.joey.repository.UserRepository;
import orz.joey.repository.entity.User;
import orz.joey.service.UserService;
import orz.joey.service.dto.UserDto;
import org.springframework.stereotype.Service;
import orz.joey.service.dto.common.ErrorCode;
import orz.joey.service.exception.BusinessException;

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
        Optional<User> userOptional = userRepository.findById(userId);
        userOptional.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        //po -> dto
        return userOptional.map(user -> {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setUsername(user.getUsername());
            userDto.setCellphone(user.getCellphone());
            userDto.setEmail(user.getEmail());
            return userDto;
        }).get();
    }
}
