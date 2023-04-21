package uz.tafakkoor.easyorder.repositories.user;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.tafakkoor.easyorder.domains.user.UserPermission;

public interface UserPermissionRepository extends JpaRepository<UserPermission, Integer> {
}