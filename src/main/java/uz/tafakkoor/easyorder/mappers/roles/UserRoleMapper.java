package uz.tafakkoor.easyorder.mappers.roles;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uz.tafakkoor.easyorder.domains.user.UserRole;
import uz.tafakkoor.easyorder.dtos.roles.UserRoleCreateDTO;

@Mapper
public interface UserRoleMapper {
    UserRoleMapper ROLE_MAPPER = Mappers.getMapper(UserRoleMapper.class);

    UserRole fromUserCreateDTOtoUserRole(UserRoleCreateDTO dto);
}
