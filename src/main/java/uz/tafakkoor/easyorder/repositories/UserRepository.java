package uz.tafakkoor.easyorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import uz.tafakkoor.easyorder.domains.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByPhoneNumber(String username);


}