package uz.tafakkoor.easyorder.domains.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import uz.tafakkoor.easyorder.domains.Auditable;
import uz.tafakkoor.easyorder.domains.Document;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Long restaurantID;
    @OneToOne
    private Document image;

    @Builder(builderMethodName = "categoryBuilder")
    public Category(Long createdBy, Long updateBy, LocalDateTime createdAt, LocalDateTime updatedAt,
                    boolean isDeleted, Long id, String name, String description, Document image, Long restaurantID) {
        super(createdBy, updateBy, createdAt, updatedAt, isDeleted);
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.restaurantID = restaurantID;
    }
}
