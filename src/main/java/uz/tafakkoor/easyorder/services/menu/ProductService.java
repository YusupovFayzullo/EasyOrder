package uz.tafakkoor.easyorder.services.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tafakkoor.easyorder.domains.menu.Category;
import uz.tafakkoor.easyorder.domains.menu.Product;
import uz.tafakkoor.easyorder.dtos.menu.product.ProductCreateDTO;
import uz.tafakkoor.easyorder.dtos.menu.product.ProductUpdateDTO;
import uz.tafakkoor.easyorder.exceptions.ItemNotFoundException;
import uz.tafakkoor.easyorder.repositories.DocumentRepository;
import uz.tafakkoor.easyorder.repositories.menu.CategoryRepository;
import uz.tafakkoor.easyorder.repositories.menu.ProductRepository;

import java.util.List;

import static uz.tafakkoor.easyorder.mappers.menu.ProductMapper.PRODUCT_MAPPER;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final DocumentRepository documentRepository;

    public Product createProduct(ProductCreateDTO dto) {
        Product product = PRODUCT_MAPPER.toProductEntity(dto);
        long categoryID = dto.getCategoryID();
        Category category = categoryRepository
                .findById(categoryID)
                .orElseThrow(() -> new ItemNotFoundException("Category not found by id %d".formatted(categoryID)));
        documentRepository.findById(dto.getImageID()).ifPresent(product::setImage);
        product.setCategory(category);
        return productRepository.save(product);
    }

    public Product getProductById(Long id, Long categoryID) {
        return productRepository.findByProductId(id, categoryID)
                .orElseThrow(() -> new ItemNotFoundException("Product not found with id " + id));
    }

    public Product getProductByName(String name, Long categoryID) {
        return productRepository.findByProductName(name, categoryID)
                .orElseThrow(() -> new ItemNotFoundException("Product not found with id %S".formatted(name)));
    }


    public List<Product>
    getAllProducts(Long categoryID) {
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

        documentRepository.findById(dto.getImageId()).ifPresent(productDB::setImage);

        PRODUCT_MAPPER.toUpdateProductEntity(dto, productDB);
        return productRepository.save(productDB);
    }
}
