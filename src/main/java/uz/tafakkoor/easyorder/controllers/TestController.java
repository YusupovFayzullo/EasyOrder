package uz.tafakkoor.easyorder.controllers;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ParameterObject
public class TestController {
    @GetMapping("/")
    public String sayHello(){
        return "Hello bro!";
    }
}
