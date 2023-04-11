package uz.tafakkoor.easyorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tafakkoor.easyorder.domains.user.UserRole;


public interface UserRolesRepository extends JpaRepository<UserRole, Short> {

}