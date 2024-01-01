package com.adldoost.caracallanguage.usecase.submitLearningBoxExamResultUseCase;

import com.adldoost.caracallanguage.model.UserWord;
import com.adldoost.caracallanguage.model.UserWordSource;
import com.adldoost.caracallanguage.repository.UserWordSourceRepository;
import com.adldoost.caracallanguage.usecase.UseCase;
import com.adldoost.caracallanguage.usecase.submitLearningBoxExamResultUseCase.dto.SubmitLearningBoxExamResultRequest;
import com.adldoost.caracallanguage.usecase.submitLearningBoxExamResultUseCase.dto.SubmitLearningBoxExamResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
@Slf4j
public class SubmitLearningBoxExamResultUseCase implements UseCase<SubmitLearningBoxExamResultRequest, SubmitLearningBoxExamResultResponse> {

    private final UserWordSourceRepository userWordSourceRepository;

    @Override
    public SubmitLearningBoxExamResultResponse execute(SubmitLearningBoxExamResultRequest request) {

        UserWordSource userWordSource = userWordSourceRepository.findByIdAndUser(
                        request.getUserWordSourceId(),
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())
                .orElseThrow(() -> new RuntimeException("user word source not found"));

        if(request.getResult() == null) {
            return new SubmitLearningBoxExamResultResponse().setLearningBoxSize(userWordSource.getLearningBox().size());
        }

        UserWord userWord = userWordSource.getLearningBox().stream().filter(w -> w.getWord().getId().equals(request.getWordId()))
                .findFirst().orElseThrow(() -> new RuntimeException("word not found in learning box"));

         //update word score
        if (request.getResult()) {
            userWord.setScore(userWord.getScore() + 1);
        } else {
            userWord.setScore(userWord.getScore() - 1);
        }

        //replace word with a new word if score is >= 10
        if (userWord.getScore() >= 10) {
            userWordSource.getLearningBox().remove(userWord);
            Collections.shuffle(userWordSource.getUserWords());
            for (UserWord w : userWordSource.getUserWords()) {
                if (userWordSource.getLearningBox().contains(w)
                        && w.getScore() == 0) {
                    userWordSource.getLearningBox().add(w);
                    break;
                }
            }
        }
        //update word in user words
        userWordSource.getUserWords().stream().filter(w -> w.getWord().getId().equals(userWord.getWord().getId()))
                .findFirst().orElseThrow(() -> new RuntimeException("word not found in user words"))
                .setScore(userWord.getScore());
        userWordSourceRepository.save(userWordSource);
        return new SubmitLearningBoxExamResultResponse().setLearningBoxSize(userWordSource.getLearningBox().size());
    }
}
