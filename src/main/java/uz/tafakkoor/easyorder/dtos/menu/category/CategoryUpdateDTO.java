package uz.tafakkoor.easyorder.dtos.menu.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Nullable;
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
@ToString
@ParameterObject
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryUpdateDTO {

    @NotNull(message = "Category Id cannot be null")
    private Long categoryID;

    @NotNull(message = "Restaurant Id cannot be null")
    private Long restaurantID;

    @Size(min = 3, max = 35, message = "Category name size must be 3-35 character")
    private String name;

    @Size(max = 200, message = "Category description must be less than 200 character")
    private String description;

    private Long imageID;

}
