package uz.tafakkoor.easyorder.domains.menu;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.tafakkoor.easyorder.domains.Auditable;
import uz.tafakkoor.easyorder.domains.user.User;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Basket extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @OneToMany(mappedBy = "basket")
    @JsonBackReference
    private Collection<ProductOrder> orders;
    @OneToOne
    private User owner;
    @Builder(builderMethodName = "basketBuilder")
    public Basket(Long createdBy, Long updateBy, LocalDateTime createdAt, LocalDateTime updatedAt, boolean isDeleted, Long id, Collection<ProductOrder> orders, User owner) {
        super(createdBy, updateBy, createdAt, updatedAt, isDeleted);
        this.id = id;
        this.orders = orders;
        this.owner = owner;
    }
}
