package com.example.demo.greeting.web;


import com.example.demo.greeting.model.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        //return new Greeting(counter.incrementAndGet(), String.format(template, name));
        return  Greeting.builder()
                .id(counter.incrementAndGet())
                //.content(String.format(template, name))
                //.content("Hello ,"+name+"!")
                .content(new StringBuilder(3).append("Hello,").append(name).append("!").toString())
                .build();
    }
}

