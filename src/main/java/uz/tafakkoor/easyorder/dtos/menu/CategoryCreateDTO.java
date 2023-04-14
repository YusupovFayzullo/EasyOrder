package uz.tafakkoor.easyorder.dtos.menu;

import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class CategoryCreateDTO {
    private String name;
    private String description;
    private MultipartFile image;
    private Long restaurantID;
}
