package uz.tafakkoor.easyorder;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.tafakkoor.easyorder.domains.user.User;
import uz.tafakkoor.easyorder.domains.user.UserRole;
import uz.tafakkoor.easyorder.enums.UserStatus;
import uz.tafakkoor.easyorder.repositories.user.UserRepository;

import java.util.List;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(title = "EasyOrder API", version = "v1", description = "API for ordering food at restaurants and cafes"),
        servers = {
                @Server(url = "http://localhost:8080", description = "Local server"),
                @Server(url = "https://api.tafakkoor.uz", description = "Production server"),
                @Server(url = "https://test.tafakkoor.uz", description = "Test server")
        }
)
public class EasyOrderApplication {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public EasyOrderApplication(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        System.out.println();
        SpringApplication.run(EasyOrderApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            userRepository.save(
                    User.builder()
                            .email("test@gmail.com")
                            .firstName("test")
                            .lastName("test")
                            .password(passwordEncoder.encode("123"))
                            .phoneNumber("+998901234567")
                            .status(UserStatus.ACTIVE)
                            .build()
            );
        };
    }

}
