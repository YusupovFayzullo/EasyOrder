package uz.tafakkoor.easyorder.services.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tafakkoor.easyorder.config.security.SessionUser;
import uz.tafakkoor.easyorder.domains.menu.Product;
import uz.tafakkoor.easyorder.domains.menu.ProductOrder;
import uz.tafakkoor.easyorder.dtos.menu.product.ProductOrderCreateDTO;
import uz.tafakkoor.easyorder.dtos.menu.product.ProductOrderUpdateDTO;
import uz.tafakkoor.easyorder.repositories.BasketRepository;
import uz.tafakkoor.easyorder.repositories.menu.ProductOrderRepository;

@Service
@RequiredArgsConstructor
public class ProductOrderService {
    private final ProductOrderRepository productOrderRepository;
    private final ProductService productService;
    private final BasketRepository basketRepository;
    private final SessionUser sessionUser;

    public ProductOrder create(ProductOrderCreateDTO dto) {
        ProductOrder productOrder = ProductOrder.builder()
                .product(productService.getProductById(dto.getProductId())) //productService needed
                .basket(basketRepository.findByOwner_Id(sessionUser.id()))    //here we get basket from basketService by user id which is registered with jwt token
                .quantity(dto.getQuantity())
                .build();
        return productOrderRepository.save(productOrder);
    }

    public ProductOrder update(ProductOrderUpdateDTO dto) {
        ProductOrder productOrder = getById(dto.getProductId());
        productOrder.setQuantity(dto.getQuantity());
        if (dto.getStatus() != null) {
            productOrder.setStatus(dto.getStatus());
        }
        return productOrderRepository.save(productOrder);
    }

    public ProductOrder getById(Long id) {
        return productOrderRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found with id %s ".formatted(id)));
    }

    public void delete(Long id) {
        productOrderRepository.delete(id);
    }


}
