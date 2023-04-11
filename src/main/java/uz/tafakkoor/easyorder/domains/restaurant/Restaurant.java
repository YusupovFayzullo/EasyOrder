package uz.tafakkoor.easyorder.domains.restaurant;

import jakarta.persistence.*;
import lombok.*;
import uz.tafakkoor.easyorder.domains.Auditable;
import uz.tafakkoor.easyorder.domains.Image;
import uz.tafakkoor.easyorder.enums.RestaurantStatus;

import java.time.LocalTime;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Restaurant extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
    private Address address;
    private String description;
    @OneToMany(cascade = CascadeType.MERGE/*, fetch = FetchType.EAGER*/)
    private Collection<Image> image;
    private String phoneNumber;
    private String email;
    private LocalTime openTime;
    private LocalTime closeTime;
    @Enumerated(EnumType.STRING)
    private RestaurantStatus status = RestaurantStatus.INACTIVE;





}
