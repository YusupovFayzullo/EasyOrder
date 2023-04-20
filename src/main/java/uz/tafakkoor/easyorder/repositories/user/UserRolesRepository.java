package uz.tafakkoor.easyorder.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import uz.tafakkoor.easyorder.domains.user.UserRole;


public interface UserRolesRepository extends JpaRepository<UserRole, Short> {
    @Query("select u from UserRole u where u.code = ?1")
    UserRole findByCode(@NonNull String code);
}