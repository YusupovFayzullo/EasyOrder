package uz.tafakkoor.easyorder.repositories.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tafakkoor.easyorder.domains.restaurant.Restaurant;

public interface RestaurantRepository extends JpaRepository <Restaurant,Long> {


}
