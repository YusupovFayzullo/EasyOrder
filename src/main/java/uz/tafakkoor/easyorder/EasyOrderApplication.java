package uz.tafakkoor.easyorder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EasyOrderApplication {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("Hello World!2");
        System.out.println("Hello World!3");
        SpringApplication.run(EasyOrderApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            System.out.println("Pro o'zgarish");
            System.out.println("test");
            System.out.println("test");
            System.out.println("test");
            System.out.println("test");
        };
    }

}
