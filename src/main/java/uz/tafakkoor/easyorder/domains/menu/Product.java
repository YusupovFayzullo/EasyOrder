package uz.tafakkoor.easyorder.domains.menu;

import jakarta.persistence.*;
import lombok.*;
import uz.tafakkoor.easyorder.domains.Auditable;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder

public class Product extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private Collection<String> imageURLs;
    private String name;
    private String description;
    private Double price;
    private Double discount;
    @Builder.Default
    private Double rating = 0.0;
    private boolean isAvailable;
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @Builder(builderMethodName = "productBuilder")
    public Product(Long createdBy, Long updateBy, LocalDateTime createdAt, LocalDateTime updatedAt,
                   boolean isDeleted, Long id, Collection<String> imageURLs, String name, String description, Double price, Double discount, Double rating, boolean isAvailable, Category category) {
        super(createdBy, updateBy, createdAt, updatedAt, isDeleted);
        this.id = id;
        this.imageURLs = imageURLs;
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.rating = rating;
        this.isAvailable = isAvailable;
        this.category = category;
    }
}
