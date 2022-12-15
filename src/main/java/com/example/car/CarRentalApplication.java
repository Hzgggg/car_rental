package com.example.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CarRentalApplication {

    @RequestMapping("/")
    public String home() {
        return "Hello This is a Test";
    }

    public static void main(String[] args) {
        SpringApplication.run(CarRentalApplication.class, args);
    }

}
