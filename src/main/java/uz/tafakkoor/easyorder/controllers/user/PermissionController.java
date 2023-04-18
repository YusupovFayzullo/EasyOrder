package uz.tafakkoor.easyorder.controllers.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.tafakkoor.easyorder.domains.user.UserPermission;
import uz.tafakkoor.easyorder.dtos.roles.UserPermissionCreateDTO;
import uz.tafakkoor.easyorder.services.roles.UserPermissionService;

@ParameterObject
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/permission")
@Tag(name = "Permission", description = "Permission API")
public class PermissionController {
    private final UserPermissionService permissionService;


    @Operation(summary = "This API used for creating a permission",
            description = "This endpoint was designed for creating a permission"
            /*,deprecated = true*/)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Permission created successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserPermission.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Unique permission code violation",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    })
    @PostMapping("/")
    public ResponseEntity<UserPermission> createPermission(@RequestBody UserPermissionCreateDTO createDTO) {
        UserPermission permission = UserPermission.builder()
                .name(createDTO.getName())
                .code(createDTO.getCode())
                .build();
        permission = permissionService.save(permission);
        return ResponseEntity.ok(permission);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserPermission> update(@PathVariable Integer id, @RequestBody UserPermissionCreateDTO createDTO) {
        UserPermission permission = UserPermission.builder()
                .id(id)
                .name(createDTO.getName())
                .code(createDTO.getCode())
                .build();
        permission = permissionService.update(permission);
        return ResponseEntity.ok(permission);
    }
}
