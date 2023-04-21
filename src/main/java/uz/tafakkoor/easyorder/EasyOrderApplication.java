package uz.tafakkoor.easyorder;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition
public class EasyOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(EasyOrderApplication.class, args);
    }

}
