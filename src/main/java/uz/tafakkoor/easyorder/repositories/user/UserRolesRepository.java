package uz.tafakkoor.easyorder.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tafakkoor.easyorder.domains.user.UserRole;


public interface UserRolesRepository extends JpaRepository<UserRole, Short> {

}