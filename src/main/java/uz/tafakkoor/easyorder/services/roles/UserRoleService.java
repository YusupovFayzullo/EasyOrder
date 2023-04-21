package uz.tafakkoor.easyorder.services.roles;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tafakkoor.easyorder.domains.user.UserRole;
import uz.tafakkoor.easyorder.dtos.roles.UserRoleCreateDTO;
import uz.tafakkoor.easyorder.exceptions.DuplicatePermissionCodeException;
import uz.tafakkoor.easyorder.exceptions.DuplicateUserRoleCodeException;
import uz.tafakkoor.easyorder.exceptions.ItemNotFoundException;
import uz.tafakkoor.easyorder.mappers.roles.UserRoleMapper;
import uz.tafakkoor.easyorder.repositories.user.UserRolesRepository;

import java.util.ArrayList;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRolesRepository userRolesRepository;

    public UserRole save(UserRoleCreateDTO dto) {
        try {
            UserRole role = UserRoleMapper.ROLE_MAPPER.fromUserCreateDTOtoUserRole(dto);
            return userRolesRepository.save(role);
        } catch (Exception e) {
            throw new DuplicateUserRoleCodeException("User role with \"%s\" code already exists".formatted(dto.getCode()));
        }
    }

    public UserRole update(Integer id, UserRoleCreateDTO dto) {
        UserRole userRole = userRolesRepository.findById(id).orElseThrow(() ->
                new ItemNotFoundException("Role with id: " + id + " not found"));
        userRole.setName(Objects.requireNonNullElse(dto.getName(), userRole.getName()));
        userRole.setCode(Objects.requireNonNullElse(dto.getCode(), userRole.getCode()));
        userRole.setId(id);
        /*if (Objects.isNull(userRole.getAuthPermissions())){
            userRole.setAuthPermissions(new ArrayList<>());
        }*/
        try {
            return userRolesRepository.save(userRole);
        }catch (Exception e){
            throw new DuplicateUserRoleCodeException("User role with %s code already exists".formatted(dto.getCode()));
        }
    }

    public UserRole get(Integer id) {
        return userRolesRepository.findById(id).orElseThrow(() ->
                new ItemNotFoundException("Role with id: " + id + " not found"));

    }

    public void delete(Integer id) {
        userRolesRepository.deleteById(id);
    }
}
