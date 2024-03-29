package uz.tafakkoor.easyorder.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tafakkoor.easyorder.domains.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByPhoneNumber(String username);

    Optional<User> findByPhoneNumberLike(String phone);
}