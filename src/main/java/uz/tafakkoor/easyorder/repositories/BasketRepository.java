package uz.tafakkoor.easyorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import uz.tafakkoor.easyorder.domains.menu.Basket;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    @Query("select b from Basket b where b.owner.id = :id")
    Basket findByOwner_Id(@Param("id") @NonNull Long id);
}