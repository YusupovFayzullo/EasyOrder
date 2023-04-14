package uz.tafakkoor.easyorder.domains.menu;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.tafakkoor.easyorder.domains.Auditable;
import uz.tafakkoor.easyorder.domains.Image;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private Collection<Image> image;
    private String name;
    private String description;
    private Double price;
    private Double discount;
    private Double rating;
    private boolean isAvailable;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Builder(builderMethodName = "productBuilder")
    public Product(Long createdBy, Long updateBy, LocalDateTime createdAt, LocalDateTime updatedAt, boolean isDeleted, Long id, Collection<Image> image, String name, String description, Double price, Double discount, Double rating, boolean isAvailable,Category category) {
        super(createdBy, updateBy, createdAt, updatedAt, isDeleted);
        this.id = id;
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.rating = rating;
        this.isAvailable = isAvailable;
        this.category = category;
    }
}
