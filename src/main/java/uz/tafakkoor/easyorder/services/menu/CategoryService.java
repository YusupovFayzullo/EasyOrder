package uz.tafakkoor.easyorder.services.menu;

import org.springframework.stereotype.Service;
import uz.tafakkoor.easyorder.domains.menu.Category;
import uz.tafakkoor.easyorder.repositories.menu.CategoryRepository;
import uz.tafakkoor.easyorder.dtos.menu.CategoryCreateDTO;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Category createCategory(CategoryCreateDTO dto) {
//        categoryRepository.save(dto);
        return null;
    }
}
