package uz.tafakkoor.easyorder.dtos.menu.product;

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
    private String name;
    private String description;
    private Double price;
    private Double discount;
    private Collection<MultipartFile> images;
}
