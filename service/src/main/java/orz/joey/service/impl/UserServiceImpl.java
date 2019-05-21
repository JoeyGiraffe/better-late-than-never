package orz.joey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import orz.joey.repository.UserRepository;
import orz.joey.repository.entity.User;
import orz.joey.service.UserService;
import orz.joey.service.dto.UserDto;
import org.springframework.stereotype.Service;
import orz.joey.service.dto.common.CustomError;
import orz.joey.service.exception.BusinessException;
import orz.joey.service.mapping.user.UserMapping;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapping userMapping;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapping userMapping) {
        this.userRepository = userRepository;
        this.userMapping = userMapping;
    }


    @Override
    public UserDto findById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        userOptional.orElseThrow(() -> new BusinessException(CustomError.USER_NOT_FOUND));
        //po -> dto
//        return userOptional.map(user -> {
//            UserDto userDto = new UserDto();
//            userDto.setId(user.getId());
//            userDto.setUsername(user.getUsername());
//            userDto.setCellphone(user.getCellphone());
//            userDto.setEmail(user.getEmail());
//            return userDto;
//        }).get();

        return userMapping.map(userOptional.get(), UserDto.class);
    }
}
