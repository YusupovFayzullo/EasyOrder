package uz.tafakkoor.easyorder.mappers.user;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uz.tafakkoor.easyorder.domains.user.User;
import uz.tafakkoor.easyorder.dtos.auth.UserCreateDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserCreateDTO toDto(User user);

    User toEntity(UserCreateDTO userDto);
}
