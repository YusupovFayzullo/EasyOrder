package uz.tafakkoor.easyorder.dtos.restaurant;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import uz.tafakkoor.easyorder.dtos.AddressDto;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class RestaurantCreateDto {

    @NotBlank(message = "name can not be blank")
    private String name;

    private AddressDto dto;



    private String description;
    private Collection<ImageDto> imageDtos;

    @NotBlank(message = "phoneNumber can not be blank")
    private String phoneNumber;

    @Email(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-]+)(\\.[a-zA-Z]{2,5}){1,2}$")
    private String email;

    private RestaurantTime openTime;

    private  RestaurantTime closeTime;


}
