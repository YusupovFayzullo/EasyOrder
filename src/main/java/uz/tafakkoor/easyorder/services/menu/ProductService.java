package uz.tafakkoor.easyorder.services.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.tafakkoor.easyorder.domains.menu.Category;
import uz.tafakkoor.easyorder.domains.menu.Product;
import uz.tafakkoor.easyorder.dtos.menu.product.ProductCreateDTO;
import uz.tafakkoor.easyorder.dtos.menu.product.ProductUpdateDTO;
import uz.tafakkoor.easyorder.exceptions.ItemNotFoundException;
import uz.tafakkoor.easyorder.repositories.menu.CategoryRepository;
import uz.tafakkoor.easyorder.repositories.menu.ProductRepository;
import uz.tafakkoor.easyorder.services.ImageService;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static uz.tafakkoor.easyorder.mappers.menu.ProductMapper.PRODUCT_MAPPER;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ImageService imageService;
    private final CategoryRepository categoryRepository;

    public Product createProduct(ProductCreateDTO dto) {
        Collection<MultipartFile> imageFiles = dto.getImages();
        Collection<String> savedImagesURLsToAWS = imageService.saveImagesToAWS(imageFiles);
        Product product = PRODUCT_MAPPER.toProductEntity(dto);
        product.setImageURLs(savedImagesURLsToAWS);
        Category category = categoryRepository.findById(dto.getCategoryID()).orElseThrow(() -> new ItemNotFoundException("Category not found by id " + dto.getCategoryID()));
        product.setCategory(category);
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Product not found with id " + id));
    }


    public List<Product> getAllProducts(Long categoryID) {
        Category category = categoryRepository
                .findById(categoryID)
                .orElseThrow(() -> new ItemNotFoundException("Category not found"));
        return productRepository
                .findProductByCategoryID(category)
                .orElseThrow(() -> new ItemNotFoundException("Product not found with categoryID " + categoryID));
    }


    public boolean deleteProduct(Long id) {

        if (productRepository.deleteProduct(id).get() == 1) {
            return true;
        } else {
            throw new RuntimeException("Product don't deleted. Try again");
        }
    }

    public Product updateProduct(ProductUpdateDTO dto, Long productID) {
        Product productDB = productRepository
                .findProduct(productID)
                .orElseThrow(
                        () -> new ItemNotFoundException("Product not found with productID " + productID));

        Collection<MultipartFile> imageFiles = dto.getImages();
        if (imageFiles != null) {
            Collection<String> savedImagesToAWS = imageService.saveImagesToAWS(imageFiles);
            productDB.setImageURLs(Objects.requireNonNullElse(savedImagesToAWS, productDB.getImageURLs()));
        }
        PRODUCT_MAPPER.toUpdateProductEntity(dto, productDB);
        return productRepository.save(productDB);
    }
}
