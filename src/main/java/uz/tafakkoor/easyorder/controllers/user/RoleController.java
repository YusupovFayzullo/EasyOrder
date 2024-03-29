package uz.tafakkoor.easyorder.controllers.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.tafakkoor.easyorder.domains.user.UserRole;
import uz.tafakkoor.easyorder.dtos.roles.UserRoleCreateDTO;
import uz.tafakkoor.easyorder.dtos.roles.UserRolePermissionDTO;
import uz.tafakkoor.easyorder.services.roles.UserRoleService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/role")
@Tag(name = "Role", description = "Play with roles")
public class RoleController {

    private final UserRoleService userRoleService;

    @Operation(summary = "This API used for creating a role",
            description = "This endpoint was designed for creating a role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Role created successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserRole.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Unique role code violation",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    })
    @PostMapping("/")
    public ResponseEntity<UserRole> create(@RequestBody @Valid UserRoleCreateDTO dto) {
        UserRole role = userRoleService.save(dto);
        return ResponseEntity.ok(role);
    }


    @Operation(summary = "This API used for updating a role",
            description = "This endpoint was designed for updating a role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role updated successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserRole.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Unique role code violation",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserRole> update(@PathVariable Integer id,
                                           UserRoleCreateDTO dto) {
        UserRole role = userRoleService.update(id, dto);
        return ResponseEntity.ok(role);
    }

    @Operation(summary = "This API was designed for getting full information of role",
            description = "This endpoint was designed for getting full information of role")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role found successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserRole.class)
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "Role not found",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserRole> get(@PathVariable Integer id) {
        UserRole role = userRoleService.get(id);
        return ResponseEntity.ok(role);
    }


    @Operation(summary = "This API was designed for deleting a role",
            description = "This endpoint was designed for deleting a role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Role deleted successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserRole.class)
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "Role not found",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<UserRole> delete(@PathVariable Integer id) {
        userRoleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "This API was designed for getting all roles",
            description = "This endpoint was designed for getting all roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles found successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserRole.class)
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "Roles not found",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    })
    @GetMapping("/roles")
    public ResponseEntity<List<UserRole>> roles() {
        List<UserRole> roleList = userRoleService.getRoles();
        return ResponseEntity.ok(roleList);
    }

    @Operation(summary = "This api is used for adding multiple permissions to role",
            description = "This endpoint is used for adding multiple permissions to role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permission added successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserRole.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    })
    @PostMapping("/addPermission")
    public ResponseEntity<UserRole> addPermission(@RequestBody @Valid UserRolePermissionDTO dto) {
        UserRole role = userRoleService.addPermission(dto);
        return ResponseEntity.ok(role);
    }


    @Operation(summary = "This api is used for removing multiple permissions from role",
            description = "This endpoint is used for removing multiple permissions from role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permission removed successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserRole.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    })
    @PostMapping("/removePermission")
    public ResponseEntity<UserRole> removePermission(@RequestBody @Valid UserRolePermissionDTO dto) {
        UserRole role = userRoleService.removePermission(dto);
        return ResponseEntity.ok(role);
    }
}
