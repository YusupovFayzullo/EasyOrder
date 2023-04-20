package uz.tafakkoor.easyorder.dtos.menu.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryCreateDTO {

    @NotBlank(message = "Category name must not be blank")
    @Min(value = 2, message = "Restaurant name size must be greater than 2")
    @Max(value = 50, message = "Restaurant name size must be less than 50")
    private String name;

    @Max(value = 200, message = "Restaurant description must be less than 200 character")
    private String description;

    //    @Size(max = 2 * 1024 * 1024, message = "Image size must be less than 2MB")
    private MultipartFile image;

    //    @NotBlank(message = "Restaurant ID must not be blank")
    private Long restaurantID;

}
