package uz.tafakkoor.easyorder.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tafakkoor.easyorder.domains.user.UserPermission;

public interface UserPermissionRepository extends JpaRepository<UserPermission, Integer> {
}