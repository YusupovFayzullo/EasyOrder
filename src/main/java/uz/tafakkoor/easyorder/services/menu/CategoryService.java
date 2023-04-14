package uz.tafakkoor.easyorder.services.menu;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tafakkoor.easyorder.domains.image.FileUpload;
import uz.tafakkoor.easyorder.domains.image.Image;
import uz.tafakkoor.easyorder.domains.menu.Category;
import uz.tafakkoor.easyorder.dtos.menu.CategoryCreateDTO;
import uz.tafakkoor.easyorder.exceptions.ItemNotFoundException;
import uz.tafakkoor.easyorder.repositories.menu.CategoryRepository;
import uz.tafakkoor.easyorder.services.ImageService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static uz.tafakkoor.easyorder.mappers.menu.CategoryMapper.CATEGORY_MAPPER;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ImageService imageService;
    private final AmazonS3 amazonS3;


    public Category createCategory(CategoryCreateDTO dto) {
        try {
            String fileName = dto.getImage().getOriginalFilename();
            byte[] fileData = dto.getImage().getBytes();
            String fileType = dto.getImage().getContentType();
            FileUpload fileUpload = FileUpload.builder()
                    .fileName(fileName)
                    .fileData(fileData)
                    .fileType(fileType).build();
            PutObjectResult easyorder = amazonS3.putObject("easyorder", fileName, Arrays.toString(fileData));
            System.out.println("easyorder = " + easyorder);
            return new Category();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        Image image = imageService.saveImage(dto.getImage());
//        Category category = CATEGORY_MAPPER.toCategoryCreate(dto);
//        category.setImage(image);
//        return categoryRepository.save(category);
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
