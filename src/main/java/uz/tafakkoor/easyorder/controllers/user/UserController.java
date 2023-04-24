package uz.tafakkoor.easyorder.controllers.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import uz.tafakkoor.easyorder.domains.user.User;
import uz.tafakkoor.easyorder.dtos.user.UserRolesCreateDTO;
import uz.tafakkoor.easyorder.exceptions.UserNotFoundException;
import uz.tafakkoor.easyorder.repositories.user.UserRepository;
import uz.tafakkoor.easyorder.services.user.UserService;

import javax.management.relation.Role;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "User API")
public class UserController {
    private final UserService userService;

    @Operation(summary = "This API used for getting a user by id")
    @GetMapping(value = "{userId}", produces = "application/json")
    public User getUserById(@PathVariable Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));
    }


    private final UserRepository userRepository;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


    @PostMapping(value = "/addRole", consumes = "application/json", produces = "application/json")
    public User addRole(@RequestBody UserRolesCreateDTO dto) {
        return userService.addRoles(dto);
    }

    @DeleteMapping(value = "/removeRoles", consumes = "application/json", produces = "application/json")
    public User removeRoles(@RequestBody UserRolesCreateDTO dto) {
        return userService.removeRoles(dto);
    }


}


