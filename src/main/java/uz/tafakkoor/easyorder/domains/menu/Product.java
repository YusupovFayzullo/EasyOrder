package uz.tafakkoor.easyorder.domains.menu;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import uz.tafakkoor.easyorder.domains.Auditable;
import uz.tafakkoor.easyorder.domains.Document;

import java.time.LocalDateTime;

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

    @OneToOne
    private Document image;
    private String name;
    private String description;
    private Double price;
    private Double discount;
    @Builder.Default
    private Double rating = 0.0;
    private boolean isAvailable;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Category category;

    @Builder(builderMethodName = "productBuilder")
    public Product(Long createdBy, Long updateBy, LocalDateTime createdAt, LocalDateTime updatedAt, boolean isDeleted, Long id, Document image, String name, String description, Double price, Double discount, Double rating, boolean isAvailable, Category category) {
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
