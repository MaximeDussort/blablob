package com.sbp.ollamaExemple.controller;

import com.sbp.ollamaExemple.service.FileReadingService;
import com.sbp.ollamaExemple.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/model")
@RequiredArgsConstructor
public class OllamaCallController {

    private final FileReadingService fileReadingService;
    private final ResponseService responseService;
    private final OllamaChatModel chatModel;

    @PostMapping(path = "/blablob")
    public String askBlablobAQuestion(@RequestParam String question) {
        String prompt = fileReadingService.readInternalFileAsString("prompts/promptBlablob.txt");

        List<Message> messages = new ArrayList<>();
        messages.add(new SystemMessage("<start_of_turn>" + prompt + "<end_of_turn>"));
        messages.add(new UserMessage("<start_of_turn>" + question + "<end_of_turn>"));

        Prompt promptToSend = new Prompt(messages);
        Flux<ChatResponse> chatResponses = chatModel.stream(promptToSend);
        String answer = Objects.requireNonNull(chatResponses.collectList().block()).stream()
                .map(response -> response.getResult().getOutput().getContent())
                .collect(Collectors.joining(""));

        // Enregistrement de la réponse dans la base

        responseService.saveResponse("blablob", question, answer);

        return answer;
    }

}
