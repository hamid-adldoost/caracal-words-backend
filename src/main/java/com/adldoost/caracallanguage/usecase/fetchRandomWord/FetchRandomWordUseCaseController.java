package com.adldoost.caracallanguage.usecase.fetchRandomWord;

import com.adldoost.caracallanguage.usecase.fetchRandomWord.dto.FetchRandomWordUseCaseRequest;
import com.adldoost.caracallanguage.usecase.fetchRandomWord.dto.FetchRandomWordUseCaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/random-word")
@RequiredArgsConstructor
@Slf4j
public class FetchRandomWordUseCaseController {

    private final FetchRandomWordUseCase fetchRandomWordUseCase;

    @GetMapping(value = "/fetch/{sourceId}", produces = "application/json;charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public FetchRandomWordUseCaseResponse fetchRandomWord(@PathVariable String sourceId) {
        return fetchRandomWordUseCase.execute(new FetchRandomWordUseCaseRequest().setSourceId(sourceId));
    }
}
