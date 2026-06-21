package com.kkek.backend.service;

import com.kkek.backend.BackendApplication;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.http.MediaType;

@Service
public class GeminiService {

    private final BackendApplication backendApplication;

    @Value("${gemini.api.key}")
    private String apiKey;
    
    private final RestClient restClient = RestClient.create();
    GeminiService(BackendApplication backendApplication) {
        this.backendApplication = backendApplication;
    }
    public Map<String, Object> callGemini(Map<String, Object> clientPayload) {
        // Construct the official Google Gemini API URL with the key query parameter
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=" + apiKey;

        try {
            // Send a secure outbound HTTPS POST request to Google's servers
            return restClient.post()
                    .uri(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(clientPayload) // Directly forwards the payload your controller received
                    .retrieve()
                    .body(Map.class); // Expecting a JSON response that can be deserialized into a Map
        } catch (Exception e) {
            System.err.println("Error calling Gemini API: " + e.getMessage());

            return Map.of("error", "Failed to communicate with Gemini downstream service", "details", e.getMessage());
        }
    }
}
