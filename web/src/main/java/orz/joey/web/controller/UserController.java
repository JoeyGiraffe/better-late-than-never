package orz.joey.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orz.joey.service.UserService;
import orz.joey.service.dto.UserDto;
import orz.joey.service.dto.common.constant.CustomError;
import orz.joey.service.dto.validation.groups.AddUserGroup;
import orz.joey.service.dto.validation.groups.UpdateUserGroup;

import javax.validation.groups.Default;
import java.util.List;

@Validated
@RestController
@Api(tags = "用户模块")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @ApiOperation(value = "获取用户信息", notes = "根据用户ID查找", tags = "joey")
    @ApiImplicitParam(name = "id", value = "用户ID", dataType = "Long", paramType = "path", required = true, allowableValues = "range[1, infinity]", defaultValue = "1")
    @GetMapping("/info/{id}")
    public UserDto findUserById(@Range(min = 1L) @PathVariable Long id) { return userService.findById(id); }


    @ApiOperation(value = "注册新用户", notes = "成功返回注册用户信息", tags = "jelly")
    @PostMapping("/save")
    public UserDto addNewUser(@Validated({AddUserGroup.class, Default.class}) @RequestBody UserDto userDto) {
        return userService.save(userDto);
    }


    @ApiOperation(value = "删除用户", notes = "根据用户ID删除")
    @ApiImplicitParam(name = "id", value = "用户ID", dataType = "Long", paramType = "path", required = true, allowableValues = "range[1, infinity]", defaultValue = "1")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "", response = CustomError.class)
//    })
    @DeleteMapping("/delete/{id}")
    public CustomError deleteUserById(@Range(min = 1L) @PathVariable Long id) {
        return userService.deleteById(id) ? CustomError.USER_DELETE_SUCCESS : CustomError.USER_DELETE_FAIL;
    }


    @ApiOperation(value = "修改用户信息", notes = "用户ID必须")
    @PutMapping("/update")
    public UserDto updateUser(@Validated({UpdateUserGroup.class, Default.class}) @RequestBody UserDto userDto) { return userService.update(userDto); }


    @ApiOperation(value = "获取所有用户列表", notes = "暂未分页")
    @GetMapping("/list")
    public List<UserDto> findAllUsers() { return userService.findAll(); }

}
