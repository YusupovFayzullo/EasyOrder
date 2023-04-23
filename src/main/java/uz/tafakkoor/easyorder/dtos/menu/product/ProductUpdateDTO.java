package uz.tafakkoor.easyorder.dtos.menu.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.stereotype.Component;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
@ParameterObject

public class ProductUpdateDTO {

    @NotNull(message = "ProductID cannot be null")
    private Long productID;

    @NotNull(message = "CategoryID cannot be null")
    private Long categoryID;

    @Size(min = 3, max = 50, message = "Product name size must be 3-50 character")
    private String name;
    @Size(max = 200, message = "Product description must be less than 200 character")
    private String description;

    private Double price;
    //    @Size(max = 100, message = "Product discount must be less than 100%")
    private Double discount;

    private Long imageId;
}
