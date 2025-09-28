package com.lekham.services;

import com.lekham.entities.mongo.ApiLog;
import com.lekham.repositories.ApiLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class OpenAiService {

    @Value("${openai.api.key}")
    private String apiKey;

    @Autowired
    private ApiLogRepository apiLogRepository;

    private final WebClient webClient;

    public OpenAiService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://openrouter.ai/api/v1").build();
    }

    public Mono<String> chat(String userMessage) {
        ApiLog apiLogs = new ApiLog();
        apiLogs.setRequest(userMessage);
        apiLogs.setRequestTime(new Date());

        String payload = """
            {
              "model": "openai/gpt-3.5-turbo",
              "messages": [
                {"role": "user", "content": "%s"}
              ]
            }
        """.formatted(userMessage);

        return webClient.post()
                .uri("/chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .header("HTTP-Referer", "http://lekham-plus/")
                .header("X-Title", "Lekham Plus")
                .header("Content-Type", "application/json")
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    apiLogs.setResponse(response);
                    apiLogs.setResponseTime(new Date());
                    apiLogRepository.save(apiLogs); // blocking save
                    return response;
                })
                .onErrorResume(e -> {
                    apiLogs.setResponse("Something went wrong");
                    apiLogs.setResponseTime(new Date());
                    apiLogRepository.save(apiLogs); // still blocking
                    return Mono.error(e);
                });

    }

}
