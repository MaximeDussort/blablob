package com.sbp.ollamaExemple.controller;

import com.sbp.ollamaExemple.entity.Response;
import com.sbp.ollamaExemple.repositories.ResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetResponseController {

    private final ResponseRepository responseRepository;

    @GetMapping("/responses")
    public Iterable<Response> getAllResponses() {
        return responseRepository.findAll();
    }

}
