package com.adldoost.caracallanguage.usecase.fetchFromLearningBox;

import com.adldoost.caracallanguage.model.UserWord;
import com.adldoost.caracallanguage.model.UserWordSource;
import com.adldoost.caracallanguage.repository.UserWordSourceRepository;
import com.adldoost.caracallanguage.usecase.UseCase;
import com.adldoost.caracallanguage.usecase.fetchFromLearningBox.dto.FetchFromLearningBoxRequest;
import com.adldoost.caracallanguage.usecase.fetchFromLearningBox.dto.FetchFromLearningBoxResponse;
import com.adldoost.caracallanguage.usecase.fetchRandomChoiceUseCase.dto.FetchRandomChoiceRequest;
import com.adldoost.caracallanguage.usecase.fetchRandomChoiceUseCase.dto.FetchRandomChoiceResponse;
import com.adldoost.caracallanguage.usecase.fetchRandomChoiceUseCase.FetchRandomChoiceUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;


@Component
@RequiredArgsConstructor
@Slf4j
public class FetchFromLearningBoxUseCase implements UseCase<FetchFromLearningBoxRequest, FetchFromLearningBoxResponse> {

    private final UserWordSourceRepository userWordSourceRepository;
    private final FetchRandomChoiceUseCase fetchRandomChoiceUseCase;

    @Override
    public FetchFromLearningBoxResponse execute(FetchFromLearningBoxRequest request) {

        UserWordSource userWordSource = userWordSourceRepository.findByIdAndUser(
                        request.getUserWordSourceId(),
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString())
                .orElseThrow(() -> new RuntimeException("user word source not found"));
        if (CollectionUtils.isEmpty(userWordSource.getLearningBox())) {
            generateLearningBox(userWordSource);
        }
        if (request.getIndex() > (userWordSource.getLearningBox().size() - 1)) {
            throw new RuntimeException("invalid index");
        }
        UserWord selectedWord = userWordSource.getLearningBox().get(request.getIndex());
        FetchRandomChoiceResponse response = fetchRandomChoiceUseCase.execute(new FetchRandomChoiceRequest(4, selectedWord.getWord().getDestinationLanguageMeaning()));
        response.getChoices().add(selectedWord.getWord().getDestinationLanguageMeaning());
        FetchFromLearningBoxResponse learningBoxResponse = new FetchFromLearningBoxResponse();
        learningBoxResponse.setLearningBoxSize(userWordSource.getLearningBox().size());
        learningBoxResponse.setScore(selectedWord.getScore());
        learningBoxResponse.setChoices(new ArrayList<>(response.getChoices()));
        Collections.shuffle(learningBoxResponse.getChoices());
        learningBoxResponse.setExamples(selectedWord.getWord().getExamples());
        learningBoxResponse.setPronunciation(selectedWord.getWord().getPronunciation());
        learningBoxResponse.setDestinationLanguageMeaning(selectedWord.getWord().getDestinationLanguageMeaning());
        learningBoxResponse.setOriginalWord(selectedWord.getWord().getOriginalWord());
        learningBoxResponse.setSourceId(userWordSource.getWordSourceId());
        learningBoxResponse.setSourceLanguageMeaning(selectedWord.getWord().getSourceLanguageMeaning());
        learningBoxResponse.setWordId(selectedWord.getWord().getId());
        learningBoxResponse.setLearningBoxSize(userWordSource.getLearningBox().size());
        return learningBoxResponse;
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
        userWordSource.setLearningBox(learningBox);
        userWordSourceRepository.save(userWordSource);

    }
}
