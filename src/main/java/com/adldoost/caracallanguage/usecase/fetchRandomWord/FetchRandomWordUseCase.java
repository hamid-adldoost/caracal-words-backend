package com.adldoost.caracallanguage.usecase.fetchRandomWord;

import com.adldoost.caracallanguage.model.Word;
import com.adldoost.caracallanguage.model.WordSource;
import com.adldoost.caracallanguage.repository.WordSourceRepository;
import com.adldoost.caracallanguage.usecase.UseCase;
import com.adldoost.caracallanguage.usecase.fetchRandomWord.dto.FetchRandomWordUseCaseRequest;
import com.adldoost.caracallanguage.usecase.fetchRandomWord.dto.FetchRandomWordUseCaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@RequiredArgsConstructor
@Slf4j
@Component
public class FetchRandomWordUseCase implements UseCase<FetchRandomWordUseCaseRequest, FetchRandomWordUseCaseResponse> {

    private final WordSourceRepository wordSourceRepository;

    @Override
    public FetchRandomWordUseCaseResponse execute(FetchRandomWordUseCaseRequest request) {

        WordSource wordSource = wordSourceRepository.findById(request.getSourceId())
                .orElseThrow(() -> new RuntimeException("Word Source Not Found"));
        int size = wordSource.getWords().size();
        Random random = new Random();
        Word word = wordSource.getWords().get(random.nextInt(size)-1);

        Set<String> choiceSet = new HashSet<>();
        choiceSet.add(word.getDestinationLanguageMeaning());

        while (choiceSet.size() < 4) {
            choiceSet.add(wordSource.getWords().get(random.nextInt(size) - 1).getDestinationLanguageMeaning());
        }
        List<String> choices = new ArrayList<>(choiceSet);

        FetchRandomWordUseCaseResponse response = new FetchRandomWordUseCaseResponse();
        response.setWordId(word.getId())
                .setChoices(choices)
                .setOriginalWord(word.getOriginalWord())
                .setSourceId(wordSource.getId())
                .setDestinationLanguageMeaning(word.getDestinationLanguageMeaning())
                .setSourceLanguageMeaning(word.getSourceLanguageMeaning())
                .setPronunciation(word.getPronunciation())
                .setExamples(word.getExamples());
        return response;
    }
}
