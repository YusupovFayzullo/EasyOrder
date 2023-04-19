package uz.tafakkoor.easyorder.services.roles;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tafakkoor.easyorder.domains.user.UserPermission;
import uz.tafakkoor.easyorder.exceptions.DuplicatePermissionCodeException;
import uz.tafakkoor.easyorder.exceptions.ItemNotFoundException;
import uz.tafakkoor.easyorder.repositories.user.UserPermissionRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserPermissionService {
    private final UserPermissionRepository userPermissionRepository;


    public UserPermission save(UserPermission permission) {
        try {
            return userPermissionRepository.save(permission);
        } catch (Exception e) {
            throw new DuplicatePermissionCodeException("\"%s\" permission code already exists".formatted(permission.getCode()));
        }
    }

    public UserPermission update(UserPermission permission) {
        UserPermission userPermission = userPermissionRepository.findById(permission.getId())
                .orElseThrow(() -> new ItemNotFoundException("Permission with id %d not found".formatted(permission.getId())));
        userPermission.setCode(Objects.requireNonNullElse(permission.getCode(), userPermission.getCode()));
        userPermission.setName(Objects.requireNonNullElse(permission.getName(), userPermission.getName()));
        return userPermissionRepository.save(userPermission);
    }
}
