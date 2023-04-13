package uz.tafakkoor.easyorder.domains.menu;

import jakarta.persistence.*;
import lombok.*;
import uz.tafakkoor.easyorder.domains.menu.Product;
import uz.tafakkoor.easyorder.domains.menu.ProductOrder;
import uz.tafakkoor.easyorder.domains.user.User;
import uz.tafakkoor.easyorder.enums.OrderStatus;

import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @OneToMany
    private Collection<ProductOrder> orders;
    @ManyToOne
    private User orderOwner;


}
