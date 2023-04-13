package uz.tafakkoor.easyorder.controllers.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.tafakkoor.easyorder.domains.user.User;
import uz.tafakkoor.easyorder.exceptions.UserNotFoundException;
import uz.tafakkoor.easyorder.repositories.UserRepository;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "User API")
public class UserController {
    @Operation(summary = "This API used for getting a user by id")
    @GetMapping(value = "{userId}", produces = "application/json")
    public User getUserById(@PathVariable Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));
    }


    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}


