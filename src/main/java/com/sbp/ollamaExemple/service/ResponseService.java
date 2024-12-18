package com.sbp.ollamaExemple.service;

import com.sbp.ollamaExemple.entity.Response;
import com.sbp.ollamaExemple.repositories.ResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResponseService {

    private final ResponseRepository responseRepository;

    public Response saveResponse(String modelName, String question, String answer) {
        Response response = new Response();
        response.setModelName(modelName);
        response.setQuestion(question);
        response.setAnswer(answer);

        return responseRepository.save(response); // Insère dans la base H2
    }
}
