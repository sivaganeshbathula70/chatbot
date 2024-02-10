package com.example.chatbot.controller;
import com.example.chatbot.dto.ChatRequest;
import com.example.chatbot.dto.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
@RequestMapping("/bot")
public class BotController {


    @Value(("${openai.api.url}"))
    private String apiURL;

    @Autowired
    private RestTemplate template;


    @GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt) {
        ChatRequest request = new ChatRequest("gpt-3.5-turbo", prompt);
        ChatResponse chatGptResponse = template.postForObject(apiURL, request, ChatResponse.class);
        return chatGptResponse.getChoices().get(0).getMessage().getContent();
    }
}
