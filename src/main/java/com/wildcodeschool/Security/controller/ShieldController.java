package com.wildcodeschool.Security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShieldController {

    @GetMapping("/")
    public String welcome() {
        return "Welcome to the SHIELD";
    }

    @GetMapping("/avengers/assemble")
    public String assemble() {
        return "Avengers... assemble";
    }
    
    @GetMapping("/secret-base")
    public String secretBase() {
        return "WCS Bases : Bordeaux, Lyon, Paris, Nantes, Lille, Toulouse";
    }
}
