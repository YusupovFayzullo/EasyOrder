package uz.tafakkoor.easyorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.tafakkoor.easyorder.domains.user.OTP;

import java.util.Optional;

public interface OTPRepository extends JpaRepository<OTP, Long> {
    @Query("select o from OTP o where o.userID = ?1 and o.code = ?2")
    Optional<OTP> findByUserIDAndCode(Long id, String code);
}