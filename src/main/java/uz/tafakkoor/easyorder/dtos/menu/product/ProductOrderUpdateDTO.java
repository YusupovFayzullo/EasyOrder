package uz.tafakkoor.easyorder.dtos.menu.product;

import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.tafakkoor.easyorder.enums.OrderStatus;

@Getter
@Setter
@Builder
public class ProductOrderUpdateDTO {
    private Long productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    private OrderStatus status;
}
