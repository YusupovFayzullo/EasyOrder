package uz.tafakkoor.easyorder.dtos.user;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRolesCreateDTO {
    private Long userId;
    private List<Integer> roleIds;
}
