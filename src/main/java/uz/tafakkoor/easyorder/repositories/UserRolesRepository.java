package uz.tafakkoor.easyorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tafakkoor.easyorder.domains.user.UserRoles;

public interface UserRolesRepository extends JpaRepository<UserRoles, Short> {

}