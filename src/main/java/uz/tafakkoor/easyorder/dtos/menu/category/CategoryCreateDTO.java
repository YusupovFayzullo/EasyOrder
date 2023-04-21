package uz.tafakkoor.easyorder.dtos.menu.category;

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
public class CategoryCreateDTO {
    @NotNull(message = "Restaurant name must not be blank")
    @Size(min = 3, max = 50, message = "Restaurant name size must be less than 50")
    private String name;

    @Size(max = 200, message = "Restaurant description must be less than 200 character")
    private String description;


    @NotNull(message = "Restaurant ID must not be blank")
    private Long restaurantID;


    private Long imageID;


}
