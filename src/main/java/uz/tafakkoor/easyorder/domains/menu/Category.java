package uz.tafakkoor.easyorder.domains.menu;

import jakarta.persistence.*;
import lombok.*;
import uz.tafakkoor.easyorder.domains.Image;
import uz.tafakkoor.easyorder.domains.restaurant.Restaurant;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToOne
    private Image image;
    @ManyToOne
    private Restaurant restaurant;
}
