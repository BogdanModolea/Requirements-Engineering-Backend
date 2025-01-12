package com.example.internship.controller;

import com.example.internship.dto.StringDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @GetMapping("/test")
    public StringDTO test() {
        return new StringDTO( "Hello World!");
    }
}
