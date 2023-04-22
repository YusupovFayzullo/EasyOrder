package uz.tafakkoor.easyorder.dtos.roles;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRolePermissionDTO {
    @NonNull
    private Integer userRoleId;
    @NonNull
    private List<Integer> userPermissionId;
}
