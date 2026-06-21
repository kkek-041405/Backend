package com.kkek.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.kkek.backend.service.GeminiService;
import java.util.Map;


@RestController
public class PAIController {

    private final GeminiService geminiService;

    public PAIController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @GetMapping("/")
    public String home() {
        System.out.println("4");
        return new String("4");
    }

   @PostMapping("/PAI")
    public Map<String, Object> postPAI(@RequestBody Map<String, Object> payload) { // <-- Make sure @RequestBody is present
        System.out.println("POST request received: " + payload);

        return geminiService.callGemini(payload);
    }

    @GetMapping("/PAI")
    public String getPAI(){
        return "GET request received";
    }
}

