package orz.joey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import orz.joey.repository.UserRepository;
import orz.joey.repository.entity.User;
import orz.joey.service.UserService;
import orz.joey.service.dto.EditUserDto;
import orz.joey.service.dto.UserDto;
import org.springframework.stereotype.Service;
import orz.joey.service.dto.common.CustomError;
import orz.joey.service.exception.BusinessException;
import orz.joey.service.mapping.user.UserMapping;

import java.util.List;

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
    public UserDto save(UserDto userDto) {
        User user = userMapping.map(userDto, User.class);
        User userSaved = userRepository.save(user);
        return userMapping.map(userSaved, UserDto.class);
    }

    @Override
    public boolean deleteById(Long userId) {
        userRepository.deleteById(userId);
        return true;
    }

    @Override
    public EditUserDto update(EditUserDto editUserDto) {
        User userToUpdate = userRepository.findById(editUserDto.getId()).orElseThrow(() -> new BusinessException(CustomError.USER_NOT_FOUND));
        userMapping.map(editUserDto, userToUpdate);
        User userUpdated = userRepository.save(userToUpdate);
        return userMapping.map(userUpdated, EditUserDto.class);
    }

    @Override
    public UserDto findById(Long userId) {
        return userMapping.map(userRepository.findById(userId).orElseThrow(() -> new BusinessException(CustomError.USER_NOT_FOUND)), UserDto.class);
    }

    @Override
    public List<UserDto> findAll() {
        return userMapping.mapAsList(userRepository.findAll(), UserDto.class);
    }
}
