package uz.tafakkoor.easyorder.dtos.image;


import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class ImageCreateDTO {
    private String originalName;
    private String generatedName;
    private String url;
    private String type;
    private Integer size;
}
