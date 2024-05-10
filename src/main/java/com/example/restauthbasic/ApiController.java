package com.example.restauthbasic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/public/hello")
    public String publicEndpoint() {
        return "Hello from public endpoint";
    }

    @GetMapping("/private/hello")
    public String privateEndpoint() {
        return "Hello from private endpoint";
    }
}
