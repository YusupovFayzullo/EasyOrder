package uz.tafakkoor.easyorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tafakkoor.easyorder.domains.user.UserPermission;

public interface UserPermissionRepository extends JpaRepository<UserPermission, Short> {
}