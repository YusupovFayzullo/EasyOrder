package uz.tafakkoor.easyorder.dtos.menu.product;

import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductOrderCreateDTO {
    private Long productId;

    @Min(value = 1,message = "Quantity must be at least 1")
    private Integer quantity;
}
