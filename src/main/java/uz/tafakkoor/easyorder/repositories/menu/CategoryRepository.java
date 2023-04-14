package uz.tafakkoor.easyorder.repositories.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.tafakkoor.easyorder.domains.menu.Category;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryById(Long id);

    @Query("from Category c where c.id = ?1 and c.restaurant.id = ?2")
    Optional<Category> findCategory(Long id, Long restaurantId);

    @Query("from Category c where c.restaurant.id = ?1")
    Optional<List<Category>> findCategoryByRestaurantId(Long restaurantID);


    @Query("delete from Category c where c.id = ?1 and c.restaurant.id = ?2")
    Optional<Category> deleteCategory(Long id, Long restaurantID);
}
