package uz.tafakkoor.easyorder.dtos.roles;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRolePermissionDTO {
    private Integer userRoleId;
    private List<Integer> userPermissionId;
}
