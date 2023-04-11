package uz.tafakkoor.easyorder.domains.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.tafakkoor.easyorder.domains.Auditable;
import uz.tafakkoor.easyorder.enums.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends Auditable implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Collection<UserRoles> roles;
    private LocalDateTime lastLogin;
    private boolean isOAuthUser;
    private boolean isBlocked;
    @Builder(builderMethodName = "childBuilder")
    public User(Long createdBy, Long updateBy, LocalDateTime createdAt, LocalDateTime updatedAt, Long id, String email, String password, String firstName, String lastName, String phoneNumber, Collection<UserRoles> roles, LocalDateTime lastLogin, boolean isOAuthUser, boolean isBlocked) {
        super(createdBy, updateBy, createdAt, updatedAt);
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.lastLogin = lastLogin;
        this.isOAuthUser = isOAuthUser;
        this.isBlocked = isBlocked;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<UserRoles> userRoles = Objects.requireNonNullElse(getRoles(), Collections.<UserRoles>emptySet());
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userRoles.forEach(authRole -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + authRole.getCode()));
            Collection<UserPermission> permissions = Objects.requireNonNullElse(authRole.getAuthPermissions(), Collections.<UserPermission>emptySet());
            permissions.forEach(authPermission -> {
                authorities.add(new SimpleGrantedAuthority(authPermission.getCode()));
            });
        });
        return authorities;
    }

    @Override
    public String getUsername() {
        return getPhoneNumber();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isBlocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !isBlocked();
    }
}
