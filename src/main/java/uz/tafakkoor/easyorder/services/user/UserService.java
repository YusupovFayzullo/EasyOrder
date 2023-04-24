package uz.tafakkoor.easyorder.services.user;

import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.tafakkoor.easyorder.domains.user.User;
import uz.tafakkoor.easyorder.domains.user.UserRole;
import uz.tafakkoor.easyorder.dtos.user.UserRolesCreateDTO;
import uz.tafakkoor.easyorder.exceptions.ItemNotFoundException;
import uz.tafakkoor.easyorder.exceptions.UserNotFoundException;
import uz.tafakkoor.easyorder.repositories.user.UserRepository;
import uz.tafakkoor.easyorder.repositories.user.UserRolesRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;

    public UserService(UserRepository userRepository, UserRolesRepository userRolesRepository) {
        this.userRepository = userRepository;
        this.userRolesRepository = userRolesRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumber(username);
    }

    public User addRoles(@NonNull UserRolesCreateDTO roleIds) {
        User user = userRepository.findById(roleIds.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Collection<UserRole> roles = user.getRoles();
        Collection<UserRole> newRoles = roleIds.getRoleIds().stream().map(roleId -> {
            return userRolesRepository.findById(roleId).orElseThrow(() -> new ItemNotFoundException("Role not found"));
        }).toList();
        roles.addAll(newRoles);
        return userRepository.save(user);

    }

    public User removeRoles(UserRolesCreateDTO dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Collection<UserRole> roles = user.getRoles();
        Collection<UserRole> newRoles = dto.getRoleIds().stream().map(roleId -> {
            return userRolesRepository.findById(roleId).orElseThrow(() -> new ItemNotFoundException("Role not found"));
        }).toList();
        roles.removeAll(newRoles);
        return userRepository.save(user);
    }
}
