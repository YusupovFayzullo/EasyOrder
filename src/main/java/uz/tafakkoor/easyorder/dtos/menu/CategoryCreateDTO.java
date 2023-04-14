package uz.tafakkoor.easyorder.dtos.menu;

import lombok.*;
import org.springframework.stereotype.Component;
import uz.tafakkoor.easyorder.domains.Image;
import uz.tafakkoor.easyorder.dtos.image.ImageCreateDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class CategoryCreateDTO {
    private String name;
    private String description;
    private ImageCreateDTO imageDTO;
    private Long restaurantID;
}
