package com.adldoost.caracallanguage.usecase.fetchFromLearningBox;

import com.adldoost.caracallanguage.model.UserWord;
import com.adldoost.caracallanguage.model.UserWordSource;
import com.adldoost.caracallanguage.model.Word;
import com.adldoost.caracallanguage.repository.UserWordSourceRepository;
import com.adldoost.caracallanguage.usecase.UseCase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
@Slf4j
public class FetchFromLearningBoxUseCase implements UseCase<FetchFromLearningBoxRequest, FetchFromLearningBoxResponse> {

    private final UserWordSourceRepository userWordSourceRepository;
    @Override
    public FetchFromLearningBoxResponse execute(FetchFromLearningBoxRequest request) {

        UserWordSource userWordSource = userWordSourceRepository.findByWordSourceIdAndUser(request.getUserWordSourceId(),
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())
                .orElseThrow(() -> new RuntimeException("user word source not found"));
        if(CollectionUtils.isEmpty(userWordSource.getLearningBox())) {
            generateLearningBox(userWordSource);
        }
        if(request.getIndex() > (userWordSource.getLearningBox().size()-1)) {
            throw new RuntimeException("invalid index");
        }
        UserWord selectedWord = userWordSource.getLearningBox().get(request.getIndex());
        return new FetchFromLearningBoxResponse()
                .setLearningBoxSize(userWordSource.getLearningBox().size())
                .setScore(selectedWord.getScore())
                .setChoices();
    }

    private void generateLearningBox(UserWordSource userWordSource) {

        List<UserWord> learningBox = new ArrayList<>();
        int learningBoxSize = 10;
        int userWordSourceSize = userWordSource.getUserWords().size();
        List<UserWord> unLearned = new ArrayList<>(userWordSource.getUserWords().stream().filter(w -> w.getScore() < 10)
                .toList());
        while ((learningBox.size() < learningBoxSize) && unLearned.size() > 0) {
            Random random = new Random();
            UserWord word = unLearned.get(random.nextInt(unLearned.size() - 1));
            unLearned.remove(word);
            learningBox.add(word);
        }

    }
}
