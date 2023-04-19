package uz.tafakkoor.easyorder.domains.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.tafakkoor.easyorder.domains.Auditable;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String imageURL;
    private Long restaurantID;

    @Builder(builderMethodName = "categoryBuilder")
    public Category(Long createdBy, Long updateBy, LocalDateTime createdAt, LocalDateTime updatedAt,
                    boolean isDeleted, Long id, String name, String description, String imageURL, Long restaurantID) {
        super(createdBy, updateBy, createdAt, updatedAt, isDeleted);
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.restaurantID = restaurantID;
    }

    public Category(Long id, String name, String description, String imageURL, Long restaurantID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.restaurantID = restaurantID;
    }
}
