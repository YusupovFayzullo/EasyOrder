package uz.tafakkoor.easyorder.dtos.restaurant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import uz.tafakkoor.easyorder.domains.Image;

import java.io.Serializable;
import java.time.LocalTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class NotBookedTableDto implements Serializable {
    private String number;
    private Integer capacity;
    private Image image;
    private String name;

    private String city;
    private String street;
    private String house;
    private String phoneNumber;



    private LocalTime openTime;
    private LocalTime closeTime;

}
