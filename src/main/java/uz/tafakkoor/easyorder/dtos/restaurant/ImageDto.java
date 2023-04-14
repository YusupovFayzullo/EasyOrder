package uz.tafakkoor.easyorder.dtos.restaurant;


import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageDto {
    private String originalName;
    private String generatedName;
    private String url;
    private String type;
    private Integer size;
}
