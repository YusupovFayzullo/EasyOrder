package uz.tafakkoor.easyorder.dtos.restaurant;


import lombok.*;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.multipart.MultipartFile;
import uz.tafakkoor.easyorder.dtos.AddressDto;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Builder
@ToString
@AllArgsConstructor
@ParameterObject
public class RestaurantCreateDto {

    //    @NotBlank(message = "name can not be blank")
    private String name;


    private AddressDto dto;


    private String description;

    private Long imageID;

    //    @NotBlank(message = "phoneNumber can not be blank")
    private String phoneNumber;

    //    @Email(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-]+)(\\.[a-zA-Z]{2,5}){1,2}$")
    private String email;

    private RestaurantTime openTime;

    private RestaurantTime closeTime;


}
