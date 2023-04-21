package uz.tafakkoor.easyorder.services.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tafakkoor.easyorder.domains.menu.Category;
import uz.tafakkoor.easyorder.domains.restaurant.Restaurant;
import uz.tafakkoor.easyorder.dtos.menu.category.CategoryCreateDTO;
import uz.tafakkoor.easyorder.dtos.menu.category.CategoryUpdateDTO;
import uz.tafakkoor.easyorder.exceptions.ItemNotFoundException;
import uz.tafakkoor.easyorder.repositories.ImageRepository;
import uz.tafakkoor.easyorder.repositories.menu.CategoryRepository;
import uz.tafakkoor.easyorder.repositories.restaurant.RestaurantRepository;

import java.util.List;

import static uz.tafakkoor.easyorder.mappers.menu.CategoryMapper.CATEGORY_MAPPER;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final RestaurantRepository restaurantRepository;
    private final ImageRepository imageRepository;

    public Category createCategory(CategoryCreateDTO dto) {

        Long dtoRestaurantID = dto.getRestaurantID();
        Category category = CATEGORY_MAPPER.toCategoryEntity(dto);
        imageRepository.findById(dto.getImageID())
                .ifPresent(category::setImage);

        Restaurant restaurant = restaurantRepository.findById(dtoRestaurantID)
                .orElseThrow(() -> new ItemNotFoundException("Restaurant not found by id=%d".formatted(dtoRestaurantID)));
        category.setRestaurantID(restaurant.getId());
        return categoryRepository.save(category);

    }

    public Category getCategoryById(Long id, Long restaurantId) {
        return categoryRepository.findCategory(id, restaurantId).orElseThrow(() -> new ItemNotFoundException("Category not found with id " + id));
    }


    public List<Category> getAllCategories(Long restaurantID) {
        return categoryRepository.findCategoryByRestaurantId(restaurantID).orElseThrow(() -> new ItemNotFoundException("Categories not found with restaurant id " + restaurantID));

    }


    public boolean deleteCategory(Long id, Long restaurantID) {

        if (categoryRepository.deleteCategory(id, restaurantID).get() == 1) {
            return true;
        } else {
            throw new RuntimeException("Category don't deleted. Try again");
        }
    }

    public Category updateCategory(CategoryUpdateDTO dto) {
        Long dtoCategoryID = dto.getCategoryID();
        Long dtoRestaurantID = dto.getRestaurantID();
        Category categoryDB = categoryRepository.findCategory(dtoCategoryID, dtoRestaurantID)
                .orElseThrow(
                        () -> new ItemNotFoundException("Category not found with categoryID=%d and restaurantID=%d".formatted(dtoCategoryID, dtoRestaurantID)));

        CATEGORY_MAPPER.toUpdateCategoryEntity(dto, categoryDB);

        imageRepository.findById(dto.getImageID()).ifPresent(categoryDB::setImage);

        return categoryRepository.save(categoryDB);
    }
}
