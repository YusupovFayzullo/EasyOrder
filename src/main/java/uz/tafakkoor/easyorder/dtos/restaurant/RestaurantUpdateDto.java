package uz.tafakkoor.easyorder.dtos.restaurant;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import uz.tafakkoor.easyorder.domains.restaurant.Address;
import uz.tafakkoor.easyorder.enums.RestaurantStatus;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantUpdateDto {
    private String name;
    private Address address;
    private String description;
    private Collection<MultipartFile> images;
    private String phoneNumber;
    private String email;
    private RestaurantCreateDto.RestaurantTime openTime;

    private RestaurantCreateDto.RestaurantTime closeTime;
    private RestaurantStatus status;


}
