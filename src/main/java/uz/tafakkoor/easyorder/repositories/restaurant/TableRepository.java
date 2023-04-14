package uz.tafakkoor.easyorder.repositories.restaurant;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.tafakkoor.easyorder.domains.restaurant.Table;

public interface TableRepository extends JpaRepository<Table,Long> {


}
