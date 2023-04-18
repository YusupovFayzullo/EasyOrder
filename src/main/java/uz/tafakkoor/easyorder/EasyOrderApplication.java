package uz.tafakkoor.easyorder;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(title = "EasyOrder API", version = "v1", description = "API for ordering food at restaurants and cafes"),
        servers = {
                @Server(url = "http://localhost:8080", description = "Local server"),
                @Server(url = "https://api.tafakkoor.uz", description = "Production server"),
                @Server(url = "https://test.tafakkoor.uz", description = "Test server")
        },
        tags = {
                @Tag(name = "Restaurant", description = "Restaurant API"),
                @Tag(name = "Order", description = "Order API"),
                @Tag(name = "Menu", description = "Menu API"),
                @Tag(name = "Category", description = "Category API"),
                @Tag(name = "Product", description = "Product API"),
                @Tag(name = "Review", description = "Review API"),
                @Tag(name = "Payment", description = "Payment API"),
                @Tag(name = "Delivery", description = "Delivery API"),
                @Tag(name = "Admin", description = "Admin API"),
                @Tag(name = "Auth", description = "Authorization API "),}
)
public class EasyOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyOrderApplication.class, args);
    }

}
