package uz.tafakkoor.easyorder.dtos.menu;

import lombok.*;
import org.springframework.stereotype.Component;
import uz.tafakkoor.easyorder.domains.Image;
import uz.tafakkoor.easyorder.domains.restaurant.Restaurant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class CategoryCreateDTO {
    private String name;
    private String description;
    private Image image;
    private Restaurant restaurant;
}
