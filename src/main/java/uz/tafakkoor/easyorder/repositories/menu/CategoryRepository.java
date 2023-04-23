package uz.tafakkoor.easyorder.repositories.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.tafakkoor.easyorder.domains.menu.Category;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {


    @Query(value = " from Category c where  c.id = ?1 and c.restaurantID = ?2")
    Optional<Category> findCategory(Long id, Long restaurantId);

    @Query(value = " from Category c where  c.restaurantID = ?1")
    Optional<List<Category>> findCategoryByRestaurantId(Long restaurantID);

    @Modifying
    @Transactional
    @Query(value = "update Category c set is_deleted=true where c.id = ?1 and c.restaurantID = ?2", nativeQuery = true)
    Optional<Integer> deleteCategory(Long id, Long restaurantID);

    @Query(value = " from Category c where  c.name = ?1 and c.restaurantID = ?2")
    Optional<Category> findCategoryByName(String name, Long restaurantID);

}
