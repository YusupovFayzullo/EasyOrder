package uz.tafakkoor.easyorder.dtos.restaurant;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springdoc.core.annotations.ParameterObject;
import uz.tafakkoor.easyorder.dtos.AddressDto;
import uz.tafakkoor.easyorder.enums.RestaurantStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ParameterObject
public class RestaurantUpdateDto {


    @NotBlank(message = "name can not be blank")
    private String name;

    private AddressDto dto;
    private String description;
    private Long imageID;

    @NotBlank(message = "phoneNumber can not be blank")
    private String phoneNumber;

    @Email(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-]+)(\\.[a-zA-Z]{2,5}){1,2}$",message = "Email is invalid")
    private String email;
    private RestaurantTime openTime;

    private RestaurantTime closeTime;
    private RestaurantStatus status;


}
