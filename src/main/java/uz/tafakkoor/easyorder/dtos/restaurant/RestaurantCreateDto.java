package uz.tafakkoor.easyorder.dtos.restaurant;



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

    @NotBlank(message = "name can not be null")
    private String name;

    private AddressDto dto;



    private String description;
    private Collection<ImageDto> imageDtos;

    @NotBlank(message = "phoneNumber can not be null")
    private String phoneNumber;

    @NotBlank(message = "email can not be null")
    private String email;

    private  RestaurantTime openTime;

    private  RestaurantTime closeTime;

    public class RestaurantTime {
        private String hour;
        private String minute;

        public String getHour() {
            return hour;
        }

        public String getMinute() {
            return minute;
        }
    }


}
