package com.kkek.backend.Controller;

import java.lang.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PAIController {

    @PostMapping("/pai")
    public String getPAI(@RequestBody String request) {
        
        System.out.println("Received PAI request: " + request);
        return "PAI data";
    }
}

