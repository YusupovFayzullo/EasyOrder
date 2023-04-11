package uz.tafakkoor.easyorder.domains.restaurant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    private String district;
    private String city;
    private String street;
    private String house;
    private Double longitude;
    private Double latitude;
}
