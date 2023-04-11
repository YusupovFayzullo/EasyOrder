package uz.tafakkoor.easyorder.domains.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String code;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "auth_role_permissions",
            joinColumns = @JoinColumn(name = "auth_role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "auth_permission_id", referencedColumnName = "id")
    )
    private Collection<UserPermission> authPermissions;


}
