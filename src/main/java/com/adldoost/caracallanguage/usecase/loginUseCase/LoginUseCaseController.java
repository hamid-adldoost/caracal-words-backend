package com.adldoost.caracallanguage.usecase.loginUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/public/login")
public class LoginUseCaseController {

    private final LoginUseCaseService loginUseCaseService;

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public LoginUseCaseResponse login(@RequestBody LoginUseCaseRequest request) {
        return loginUseCaseService.execute(request);
    }

}
