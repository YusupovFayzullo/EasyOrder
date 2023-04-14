package uz.tafakkoor.easyorder.domains.menu;

import jakarta.persistence.*;
import lombok.*;
import uz.tafakkoor.easyorder.domains.Auditable;
import uz.tafakkoor.easyorder.domains.Image;
import uz.tafakkoor.easyorder.domains.restaurant.Restaurant;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Category extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToOne
    private Image image;
    @ManyToOne
    private Restaurant restaurant;

    @Builder(builderMethodName = "categoryBuilder")
    public Category(Long createdBy, Long updateBy, LocalDateTime createdAt, LocalDateTime updatedAt, boolean isDeleted, Long id, String name, String description, Image image, Restaurant restaurant) {
        super(createdBy, updateBy, createdAt, updatedAt, isDeleted);
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.restaurant = restaurant;
    }
}
