package uz.tafakkoor.easyorder.repositories.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.tafakkoor.easyorder.domains.menu.ProductOrder;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
    @Transactional
    @Modifying
    @Query("delete from ProductOrder p where p.id = ?1")
    void delete(Long aLong);

}