package uz.tafakkoor.easyorder.repositories.restaurant;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.tafakkoor.easyorder.domains.restaurant.Table;
import uz.tafakkoor.easyorder.dtos.restaurant.NotBookedTableDto;

import java.util.List;
import java.util.Optional;

public interface TableRepository extends JpaRepository<Table, Long> {

    @Query(nativeQuery = true,
            value = "select * from tables  where is_deleted=false;",
            countQuery = "select count(0) from tables"
    )
    Page<Table> getAll(Pageable pageable);

    @Query("select t from Table t where  t.isDeleted=false and t.restaurant.id=?1 and t.number=?2")
    Optional<Table> getById(Long id, String number);


    @Query("""
            select new uz.tafakkoor.easyorder.dtos.restaurant.NotBookedTableDto(t.number,t.capacity,t.qrCodeURL,r.name,r.address.city,r.address.street,r.address.house,r.phoneNumber,r.openTime,r.closeTime) from Table  t inner join Restaurant  r
            on r.id=t.restaurant.id where t.isBooked=false and t.isDeleted=false and r.id=?1""")
    List<NotBookedTableDto> getInfoNotBooked(Long id);
    //            select new uz.tafakkoor.easyorder.dtos.restaurant.NotBookedTableDto(t.number,t.capacity, t.qrCodeURL,r.name, r.address.city, r.address.street, r.address.house,  r.phoneNumber,r.openTime, r.closeTime) from Table  t inner join Restaurant  r


}
