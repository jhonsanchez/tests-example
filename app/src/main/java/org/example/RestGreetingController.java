package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestGreetingController {
    @GetMapping("/api/greeting")
    public String greeting() {
        return "Hello, World!";
    }
}
