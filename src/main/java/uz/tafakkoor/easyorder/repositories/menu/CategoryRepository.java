package uz.tafakkoor.easyorder.repositories.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.tafakkoor.easyorder.domains.menu.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {


}
