package uz.tafakkoor.easyorder.dtos.restaurant;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springdoc.core.annotations.ParameterObject;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ParameterObject
public class TableUpdate {

    @NotBlank(message = "number can not blank")
    private String number;

    @Min(value = 2, message = "2 tadan kam bo'lmasin")
    @Max(value = 6, message = "6 tadan ko'p bo'lmasin")

    private Integer capacity;
    private boolean booked;

    private boolean isDeleted;
    private Long imageId;

    private Long restaurantId;

}
