package uz.tafakkoor.easyorder.dtos.restaurant;

import lombok.*;
import lombok.experimental.Accessors;
import uz.tafakkoor.easyorder.domains.Document;

import java.io.Serializable;
import java.time.LocalTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
public class NotBookedTableDto implements Serializable {
    private String number;
    private Integer capacity;
    private Document document;
    private String name;

    private String city;
    private String street;
    private String house;
    private String phoneNumber;


    private LocalTime openTime;
    private LocalTime closeTime;

}
