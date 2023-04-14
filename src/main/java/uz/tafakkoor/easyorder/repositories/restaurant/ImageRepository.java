package uz.tafakkoor.easyorder.repositories.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tafakkoor.easyorder.domains.Image;

public interface ImageRepository extends JpaRepository<Image,Long> {

}
