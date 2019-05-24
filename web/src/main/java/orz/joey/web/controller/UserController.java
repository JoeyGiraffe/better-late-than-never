package orz.joey.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orz.joey.service.UserService;
import orz.joey.service.dto.UserDto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info/{id}")
    public UserDto findUserById(
//            @Range(min = 1L)
            @Min(1L)
            @Max(Long.MAX_VALUE)
            @PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/save")
    public UserDto addNewUser(@Validated @RequestBody UserDto userDto) {
        return userService.save(userDto);
    }
}
