package uz.tafakkoor.easyorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tafakkoor.easyorder.domains.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

}