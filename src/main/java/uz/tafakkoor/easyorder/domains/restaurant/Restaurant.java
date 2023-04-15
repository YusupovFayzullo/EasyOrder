package uz.tafakkoor.easyorder.domains.restaurant;

import jakarta.persistence.*;
import lombok.*;
import uz.tafakkoor.easyorder.domains.Auditable;
import uz.tafakkoor.easyorder.enums.RestaurantStatus;

import java.time.LocalDateTime;
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
    @ElementCollection
    private Collection<String> imageURLs;
    private String phoneNumber;
    private String email;


    private LocalTime  openTime;
    private LocalTime closeTime;
    @Enumerated(EnumType.STRING)
    private RestaurantStatus status = RestaurantStatus.INACTIVE;

    @Builder(builderMethodName = "restaurantBuilder")
    public Restaurant(Long createdBy, Long updateBy, LocalDateTime createdAt, LocalDateTime updatedAt,
                      boolean isDeleted, Long id, String name, Address address, String description, Collection<String> imageURLs, String phoneNumber, String email, LocalTime openTime, LocalTime closeTime, RestaurantStatus status) {
        super(createdBy, updateBy, createdAt, updatedAt, isDeleted);
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.imageURLs = imageURLs;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.status = status;
    }




}
