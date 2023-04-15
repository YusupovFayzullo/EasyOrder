package uz.tafakkoor.easyorder.services.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tafakkoor.easyorder.domains.menu.Category;
import uz.tafakkoor.easyorder.dtos.menu.CategoryCreateDTO;
import uz.tafakkoor.easyorder.repositories.menu.CategoryRepository;

import static uz.tafakkoor.easyorder.mappers.menu.CategoryMapper.CATEGORY_MAPPER;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;


    public Category createCategory(CategoryCreateDTO dto) {
        Category categoryCreate = CATEGORY_MAPPER.toCategoryCreate(dto);
        return categoryRepository.save(categoryCreate);
    }
}
