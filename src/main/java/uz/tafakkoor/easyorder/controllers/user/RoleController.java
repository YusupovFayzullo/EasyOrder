package uz.tafakkoor.easyorder.controllers.user;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.tafakkoor.easyorder.domains.user.UserPermission;
import uz.tafakkoor.easyorder.domains.user.UserRole;
import uz.tafakkoor.easyorder.dtos.roles.UserRoleCreateDTO;
import uz.tafakkoor.easyorder.dtos.roles.UserRolePermissionDTO;
import uz.tafakkoor.easyorder.mappers.roles.UserRoleMapper;
import uz.tafakkoor.easyorder.services.roles.UserRoleService;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/role")
@Tag(name = "Role", description = "Play with roles")
public class RoleController {

    private final UserRoleService userRoleService;

    @PostMapping("/")
    public ResponseEntity<UserRole> create(@RequestBody UserRoleCreateDTO dto) {
        UserRole role = userRoleService.save(dto);
        return ResponseEntity.ok(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRole> update(@PathVariable Integer id,
                                           UserRoleCreateDTO dto) {
        UserRole role = userRoleService.update(id, dto);
        return ResponseEntity.ok(role);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRole> get(@PathVariable Integer id) {
        UserRole role = userRoleService.get(id);
        return ResponseEntity.ok(role);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserRole> delete(@PathVariable Integer id) {
        userRoleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/roles")
    public ResponseEntity<List<UserRole>> roles() {
        List<UserRole> roleList = userRoleService.getRoles();
        return ResponseEntity.ok(roleList);
    }


    @PostMapping("/addPermission")
    public ResponseEntity<UserRole> addPermission(@RequestBody UserRolePermissionDTO dto) {
        UserRole role = userRoleService.addPermission(dto);
        return ResponseEntity.ok(role);
    }
    @PostMapping("/removePermission")
    public ResponseEntity<UserRole> removePermission(@RequestBody UserRolePermissionDTO dto) {
        UserRole role = userRoleService.removePermission(dto);
        return ResponseEntity.ok(role);
    }


}
