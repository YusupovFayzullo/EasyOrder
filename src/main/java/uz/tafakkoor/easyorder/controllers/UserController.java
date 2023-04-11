package uz.tafakkoor.easyorder.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.tafakkoor.easyorder.domains.user.User;
import uz.tafakkoor.easyorder.exceptions.UserNotFoundException;
import uz.tafakkoor.easyorder.repositories.UserRepository;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "{userId}", produces = "application/json")
    public User getUserById(@PathVariable Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));
    }


}


