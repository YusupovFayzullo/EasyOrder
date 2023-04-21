package uz.tafakkoor.easyorder.dtos.roles;

import lombok.*;
import org.springdoc.core.annotations.ParameterObject;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ParameterObject
public class UserPermissionCreateDTO {
    private String name;
    private String code;

}