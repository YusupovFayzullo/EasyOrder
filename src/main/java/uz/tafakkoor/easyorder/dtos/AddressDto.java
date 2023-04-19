package uz.tafakkoor.easyorder.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springdoc.core.annotations.ParameterObject;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ParameterObject

public class AddressDto {

    private String district;

    @NotBlank(message = "city can not be null")
    private String city;

    @NotBlank(message = "street can not be null")
    private String street;

    @NotBlank(message = "house can not be null")
    private String house;

    private Double longitude;
    private Double latitude;

}
