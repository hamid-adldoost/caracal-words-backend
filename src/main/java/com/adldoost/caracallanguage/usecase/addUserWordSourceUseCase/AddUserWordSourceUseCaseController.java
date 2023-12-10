package com.adldoost.caracallanguage.usecase.addUserWordSourceUseCase;

import com.adldoost.caracallanguage.usecase.addUserWordSourceUseCase.dto.AddUserWordSourceUseCaseRequest;
import com.adldoost.caracallanguage.usecase.addUserWordSourceUseCase.dto.AddUserWordSourceUseCaseResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/add-user-word-source")
@RequiredArgsConstructor
@Slf4j
public class AddUserWordSourceUseCaseController {

    private final AddUserWordSourceUseCaseService addUserWordSourceUseCaseService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('user')")
    public AddUserWordSourceUseCaseResponse addUserWordSource(@RequestBody AddUserWordSourceUseCaseRequest request) {
        return addUserWordSourceUseCaseService.execute(request);
    }
}
