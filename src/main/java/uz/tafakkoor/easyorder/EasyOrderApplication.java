package uz.tafakkoor.easyorder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EasyOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyOrderApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {

        };
    }

}
