package uz.tafakkoor.easyorder.domains;

import jakarta.persistence.*;
import lombok.*;
import uz.tafakkoor.easyorder.domains.menu.Product;
import uz.tafakkoor.easyorder.domains.user.User;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double discount;
    private Double rating;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private User user;

    @ElementCollection
    @CollectionTable(name = "order_products")
    private Map<Product, Integer> products;


    enum Status {
        NEW, IN_PROGRESS, DONE, CANCELED
    }
}
