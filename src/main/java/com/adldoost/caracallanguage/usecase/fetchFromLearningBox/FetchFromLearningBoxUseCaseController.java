package com.adldoost.caracallanguage.usecase.fetchFromLearningBox;

import com.adldoost.caracallanguage.usecase.fetchFromLearningBox.dto.FetchFromLearningBoxRequest;
import com.adldoost.caracallanguage.usecase.fetchFromLearningBox.dto.FetchFromLearningBoxResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/fetch-from-learning-box")
public class FetchFromLearningBoxUseCaseController {

    private final FetchFromLearningBoxUseCase useCase;


    @GetMapping("/{userWordSourceId}")
    @ResponseStatus(HttpStatus.OK)
    public FetchFromLearningBoxResponse fetchFromLearningBox(@PathVariable String userWordSourceId,
                                                             @RequestParam Integer index) {
        return useCase.execute(new FetchFromLearningBoxRequest()
                .setIndex(index)
                .setUserWordSourceId(userWordSourceId));
    }



}
