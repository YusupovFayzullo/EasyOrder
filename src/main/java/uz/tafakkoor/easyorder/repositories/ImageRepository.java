package uz.tafakkoor.easyorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tafakkoor.easyorder.domains.Document;

public interface ImageRepository extends JpaRepository<Document, Long> {

}