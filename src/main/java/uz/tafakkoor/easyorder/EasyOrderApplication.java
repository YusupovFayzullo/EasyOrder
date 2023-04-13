package uz.tafakkoor.easyorder;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

    public static void main(String[] args) {
        System.out.println();
        SpringApplication.run(EasyOrderApplication.class, args);
    }

}
