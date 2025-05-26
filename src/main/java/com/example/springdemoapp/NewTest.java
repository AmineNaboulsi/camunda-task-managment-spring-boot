package com.example.springdemoapp;

import org.springframework.web.bind.annotation.GetMapping;

public class NewTest {


    @GetMapping("/test")
    public String Test() {
        return "Test" ;
    }

}
