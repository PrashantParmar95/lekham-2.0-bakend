package com.lekham.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lekham.dto.ChatRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/open-ai")
public class ChatController {

    @Autowired
    private com.lekham.services.OpenAiService openAiService;

    @Autowired
    private ObjectMapper objectMapper;


    @PostMapping("/prompt")
    public ResponseEntity<Map<String, Object>> chat(@RequestBody ChatRequest request) {
        try {
            String aiResponse = openAiService.chat(request.getPrompt().toString()).block(); // ← BLOCK HERE
            Map<String, Object> responseMap = objectMapper.readValue(aiResponse, Map.class);
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
