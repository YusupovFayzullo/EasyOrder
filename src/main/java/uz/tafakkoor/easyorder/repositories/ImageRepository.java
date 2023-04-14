package uz.tafakkoor.easyorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tafakkoor.easyorder.domains.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}