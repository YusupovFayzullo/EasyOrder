package uz.tafakkoor.easyorder.services.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tafakkoor.easyorder.domains.menu.Category;
import uz.tafakkoor.easyorder.domains.menu.Product;
import uz.tafakkoor.easyorder.dtos.menu.product.ProductCreateDTO;
import uz.tafakkoor.easyorder.dtos.menu.product.ProductUpdateDTO;
import uz.tafakkoor.easyorder.exceptions.ItemNotFoundException;
import uz.tafakkoor.easyorder.repositories.ImageRepository;
import uz.tafakkoor.easyorder.repositories.menu.CategoryRepository;
import uz.tafakkoor.easyorder.repositories.menu.ProductRepository;
import uz.tafakkoor.easyorder.services.ImageService;

import java.util.List;

import static uz.tafakkoor.easyorder.mappers.menu.ProductMapper.PRODUCT_MAPPER;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ImageService imageService;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;

    public Product createProduct(ProductCreateDTO dto) {

        Product product = PRODUCT_MAPPER.toProductEntity(dto);
        Category category = categoryRepository
                .findById(dto.getCategoryID())
                .orElseThrow(() -> new ItemNotFoundException("Category not found by id %d".formatted(dto.getCategoryID())));

        imageRepository.findById(dto.getImageID()).ifPresent(product::setImage);

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

    public Product updateProduct(ProductUpdateDTO dto) {
        Long dtoProductID = dto.getProductID();
        Product productDB = productRepository
                .findProduct(dtoProductID)
                .orElseThrow(() -> new ItemNotFoundException("Product not found with productID =%d".formatted(dtoProductID)));

        imageRepository.findById(dto.getImageId()).ifPresent(productDB::setImage);

        PRODUCT_MAPPER.toUpdateProductEntity(dto, productDB);
        return productRepository.save(productDB);
    }
}
