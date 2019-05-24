package orz.joey.service;

import orz.joey.service.dto.UserDto;

public interface UserService {
    UserDto findById(Long userId);

    UserDto save(UserDto userDto);
}
