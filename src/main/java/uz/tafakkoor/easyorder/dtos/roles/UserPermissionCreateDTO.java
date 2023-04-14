package uz.tafakkoor.easyorder.dtos.roles;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPermissionCreateDTO {
    private String name;
    private String code;

}