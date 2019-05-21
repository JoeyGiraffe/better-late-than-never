package orz.joey.service.mapping.user;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import orz.joey.repository.entity.User;
import orz.joey.service.dto.UserDto;

/**
 * @Description: mapping between {@link User} and {@link UserDto}.
 **/
@Component
public class UserMapping extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(User.class, UserDto.class)
                .customize(new CustomMapper<User, UserDto>() {
                    @Override
                    public void mapAtoB(User user, UserDto userDto, MappingContext context) {
                        userDto.setCellphone(user.getCellphone().substring(0, 3)+"****"+user.getCellphone().substring(user.getCellphone().length()-4));
                    }
                })
                .byDefault()
                .register();
    }
}
