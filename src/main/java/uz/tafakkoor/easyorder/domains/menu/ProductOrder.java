package uz.tafakkoor.easyorder.domains.menu;

import jakarta.persistence.*;
import lombok.*;
import uz.tafakkoor.easyorder.enums.OrderStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Builder.Default
    private OrderStatus status = OrderStatus.NEW;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Basket basket;

    private Integer quantity;
}

