package uz.tafakkoor.easyorder.dtos.menu.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
@ParameterObject
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductCreateDTO {

    @Size(min = 3, max = 50, message = "Product name size must be 3-50 character")
    private String name;
    @Size(max = 200, message = "Product description must be less than 200 character")
    private String description;

    private Double price;
    //    @Pattern(regexp = "^(100|[1-9]?[0-9])$", message = "Product discount must be less than 100%")
    private Double discount;
    @NotNull(message = "Category id cannot be null")
    private Long categoryID;

    private Long imageID;

}
