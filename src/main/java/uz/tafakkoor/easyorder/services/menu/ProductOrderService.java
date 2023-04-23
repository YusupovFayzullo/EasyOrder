package uz.tafakkoor.easyorder.services.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tafakkoor.easyorder.domains.menu.Product;
import uz.tafakkoor.easyorder.domains.menu.ProductOrder;
import uz.tafakkoor.easyorder.dtos.menu.product.ProductOrderCreateDTO;
import uz.tafakkoor.easyorder.dtos.menu.product.ProductOrderUpdateDTO;
import uz.tafakkoor.easyorder.repositories.menu.ProductOrderRepository;

@Service
@RequiredArgsConstructor
public class ProductOrderService {
    private final ProductOrderRepository productOrderRepository;

    public ProductOrder create(ProductOrderCreateDTO dto) {
        ProductOrder productOrder = ProductOrder.builder()
                //.product(productService.get(dto.getProductId())) //productService needed
                //.basket(basketService.getByOwner(id)      //here we get basket from basketService by user id which is registered with jwt token

                .product(Product.productBuilder()
                        .name("Shurva")
                        .description("o'ta go'zal")
                        .isAvailable(true)
                        .price(10_000d)
                        .build())
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
