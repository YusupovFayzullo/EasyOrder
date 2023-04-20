package uz.tafakkoor.easyorder.dtos.menu.product;

import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component

public class ProductUpdateDTO {

    @Size(min = 3, max = 50, message = "Product name size must be 3-50 character")
    private String name;
    @Size(max = 200, message = "Product description must be less than 200 character")
    private String description;

    private Double price;
    @Size(max = 100, message = "Product discount must be less than 100%")
    private Double discount;
    private Long categoryID;
}
