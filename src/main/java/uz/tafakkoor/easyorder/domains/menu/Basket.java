package uz.tafakkoor.easyorder.domains.menu;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.tafakkoor.easyorder.domains.user.User;

import java.util.Collection;

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
