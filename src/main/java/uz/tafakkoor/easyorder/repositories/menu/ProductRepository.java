package uz.tafakkoor.easyorder.repositories.menu;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.tafakkoor.easyorder.domains.menu.Category;
import uz.tafakkoor.easyorder.domains.menu.Product;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query("from Product p where p.id = ?1")
    Optional<Product> findProduct(Long id);


    @Modifying
    @Transactional
    @Query(value = "update Product c set is_deleted=true where c.id = ?1 ", nativeQuery = true)
    Optional<Integer> deleteProduct(Long id);

    @Query("from Product p where p.category = ?1")
    Optional<List<Product>> findProductByCategoryID(Category category);
}
