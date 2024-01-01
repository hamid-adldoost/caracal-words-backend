package com.adldoost.caracallanguage.usecase.submitLearningBoxExamResultUseCase;

import com.adldoost.caracallanguage.usecase.submitLearningBoxExamResultUseCase.dto.SubmitLearningBoxExamResultRequest;
import com.adldoost.caracallanguage.usecase.submitLearningBoxExamResultUseCase.dto.SubmitLearningBoxExamResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/submit-learning-box-exam-result")
public class SubmitLearningBoxExamResultController {

    private final SubmitLearningBoxExamResultUseCase useCase;

    @PostMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SubmitLearningBoxExamResultResponse submitResult(@RequestBody SubmitLearningBoxExamResultRequest request) {
        return useCase.execute(request);
    }
}
