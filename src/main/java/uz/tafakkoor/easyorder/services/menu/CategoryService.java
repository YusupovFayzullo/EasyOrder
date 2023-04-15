package uz.tafakkoor.easyorder.services.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.tafakkoor.easyorder.domains.menu.Category;
import uz.tafakkoor.easyorder.dtos.menu.CategoryCreateDTO;
import uz.tafakkoor.easyorder.exceptions.ItemNotFoundException;
import uz.tafakkoor.easyorder.repositories.menu.CategoryRepository;
import uz.tafakkoor.easyorder.services.ImageService;

import java.util.List;

import static uz.tafakkoor.easyorder.mappers.menu.CategoryMapper.CATEGORY_MAPPER;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ImageService imageService;


    public Category createCategory(CategoryCreateDTO dto) {
        MultipartFile imageFile = dto.getImage();
        String imageURL = imageService.saveImageToAWS(imageFile);
        Category category = CATEGORY_MAPPER.toCategoryCreate(dto);
        category.setImageURL(imageURL);
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id, Long restaurantId) {
        return categoryRepository.findCategory(id, restaurantId)
                .orElseThrow(() -> new ItemNotFoundException("Category not found with id " + id));
    }


    public List<Category> getAllCategories(Long restaurantID) {
        return categoryRepository.findCategoryByRestaurantId(restaurantID)
                .orElseThrow(() -> new ItemNotFoundException("Categories not found with restaurant id " + restaurantID));
    }

    public Category deleteCategory(Long id, Long restaurantID) {
        return categoryRepository.deleteCategory(id, restaurantID).orElseThrow(() -> new ItemNotFoundException("Category not found with id " + id));
    }
}
