package uz.tafakkoor.easyorder.services;

import uz.tafakkoor.easyorder.domains.user.User;
import uz.tafakkoor.easyorder.repositories.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
