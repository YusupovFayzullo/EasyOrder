package uz.tafakkoor.easyorder.repositories.restaurant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.tafakkoor.easyorder.domains.restaurant.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query(nativeQuery = true,
            value = "select * from restaurant  where is_deleted=false;",
            countQuery = "select count(0) from restaurant"
    )
    Page<Restaurant> getAll(Pageable pageable);

}
