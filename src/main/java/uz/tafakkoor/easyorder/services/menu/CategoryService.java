package uz.tafakkoor.easyorder.services.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.tafakkoor.easyorder.domains.menu.Category;
import uz.tafakkoor.easyorder.domains.restaurant.Restaurant;
import uz.tafakkoor.easyorder.dtos.menu.category.CategoryCreateDTO;
import uz.tafakkoor.easyorder.dtos.menu.category.CategoryUpdateDTO;
import uz.tafakkoor.easyorder.exceptions.ItemNotFoundException;
import uz.tafakkoor.easyorder.repositories.menu.CategoryRepository;
import uz.tafakkoor.easyorder.repositories.restaurant.RestaurantRepository;
import uz.tafakkoor.easyorder.services.ImageService;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static uz.tafakkoor.easyorder.mappers.menu.CategoryMapper.CATEGORY_MAPPER;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ImageService imageService;
    private final RestaurantRepository restaurantRepository;

    public Category createCategory(CategoryCreateDTO dto) {
        try {
            MultipartFile imageFile = dto.getImage();
            String imageURL = imageService.saveImageToServer(imageFile);
            Category category = CATEGORY_MAPPER.toCategoryEntity(dto);
            category.setImageURL(imageURL);
            Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantID()).orElseThrow(() -> new ItemNotFoundException("Restaurant not found by id " + dto.getRestaurantID()));
            category.setRestaurantID(restaurant.getId());
            return categoryRepository.save(category);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    public Category updateCategory(CategoryUpdateDTO dto, Long categoryID, Long restaurantID) {
        Category categoryDB = categoryRepository.findCategory(categoryID, restaurantID).orElseThrow(() -> new ItemNotFoundException("Category not found with categoryID " + categoryID + " and restaurantID " + restaurantID));

        MultipartFile imageFile = dto.getImage();
        if (imageFile != null) {
            String imageURL = imageService.saveImageToServer(imageFile);
            categoryDB.setImageURL(Objects.requireNonNullElse(imageURL, categoryDB.getImageURL()));
        }
        CATEGORY_MAPPER.toUpdateCategoryEntity(dto, categoryDB);
        return categoryRepository.save(categoryDB);
    }
}
