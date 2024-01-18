package com.adldoost.caracallanguage.usecase.fetchUserWordSourceListUseCase;

import com.adldoost.caracallanguage.model.UserWordSource;
import com.adldoost.caracallanguage.repository.UserWordSourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/fetch-user-word-source-list")
@RequiredArgsConstructor
@Slf4j
public class FetchUserWordSourceListController {

    private final FetchUserWordSourceListUseCase fetchUserWordSourceListUseCase;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    FetchUserWordSourceListUseCaseResponse fetchUserWordSourceListUseCase() {
        return fetchUserWordSourceListUseCase.execute(new FetchUserWordSourceListUseCaseRequest()
                .setUser(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()));
    }
}
