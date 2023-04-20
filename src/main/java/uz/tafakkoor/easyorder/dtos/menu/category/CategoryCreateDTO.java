package uz.tafakkoor.easyorder.dtos.menu.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springdoc.core.annotations.ParameterObject;
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
    @NotNull(message = "Restaurant name must not be blank")
    @Size(min = 3, max = 50, message = "Restaurant name size must be less than 50")
    private String name;

    @Size(max = 200, message = "Restaurant description must be less than 200 character")
    private String description;


    @NotNull(message = "Restaurant ID must not be blank")
    private Long restaurantID;

    private MultipartFile image;


}
