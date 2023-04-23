package uz.tafakkoor.easyorder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.tafakkoor.easyorder.domains.Document;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {


    @Query("select d from Document d where d.generatedName = ?1 and d.createdBy = ?2")
    Optional<Document> findDocument(String fileName, Long id);
}
