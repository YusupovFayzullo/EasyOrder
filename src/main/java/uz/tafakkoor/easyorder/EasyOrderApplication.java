package uz.tafakkoor.easyorder;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(title = "EasyOrder API", version = "v1", description = "API for ordering food at restaurants and cafes" ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Local server"),
                @Server(url = "https://api.tafakkoor.uz", description = "Production server"),
                @Server(url = "https://test.tafakkoor.uz", description = "Test server")
        }
        ,
        tags = {
                @io.swagger.v3.oas.annotations.tags.Tag(name = "User", description = "User API"),
                @io.swagger.v3.oas.annotations.tags.Tag(name = "Restaurant", description = "Restaurant API"),
                @io.swagger.v3.oas.annotations.tags.Tag(name = "Order", description = "Order API"),
                @io.swagger.v3.oas.annotations.tags.Tag(name = "Menu", description = "Menu API"),
                @io.swagger.v3.oas.annotations.tags.Tag(name = "Category", description = "Category API"),
                @io.swagger.v3.oas.annotations.tags.Tag(name = "Product", description = "Product API"),
                @io.swagger.v3.oas.annotations.tags.Tag(name = "Review", description = "Review API"),
                @io.swagger.v3.oas.annotations.tags.Tag(name = "Payment", description = "Payment API"),
                @io.swagger.v3.oas.annotations.tags.Tag(name = "Delivery", description = "Delivery API"),
                @io.swagger.v3.oas.annotations.tags.Tag(name = "Admin", description = "Admin API"),
                @io.swagger.v3.oas.annotations.tags.Tag(name = "Auth", description = "Authorization API "),}
)
public class EasyOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyOrderApplication.class, args);
    }

}
