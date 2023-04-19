package uz.tafakkoor.easyorder.repositories.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tafakkoor.easyorder.domains.restaurant.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
