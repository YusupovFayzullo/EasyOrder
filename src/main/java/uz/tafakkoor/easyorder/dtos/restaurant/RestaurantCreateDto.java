package uz.tafakkoor.easyorder.dtos.restaurant;


import com.beust.jcommander.Parameter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springdoc.core.annotations.ParameterObject;
import uz.tafakkoor.easyorder.dtos.AddressDto;

@Getter
@Setter
@NoArgsConstructor
@Builder

@AllArgsConstructor
@ParameterObject
public class RestaurantCreateDto {

    @NotBlank(message = "name can not be blank")
    private String name;

    @Parameter(description = "Address")
    private AddressDto dto;


    private String description;
    @NotNull(message = "imageID can not be null")
    private Long imageID;

    @Pattern(regexp = "^(\\+998|8)[ -]?\\d{2}[ -]?\\d{3}[ -]?\\d{2}[ -]?\\d{2}$")
    @NotBlank(message = "phoneNumber can not be blank")
    private String phoneNumber;

    @Email(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-]+)(\\.[a-zA-Z]{2,5}){1,2}$", message = "Email is invalid")
    private String email;

    private RestaurantTime openTime;

    private RestaurantTime closeTime;


}
