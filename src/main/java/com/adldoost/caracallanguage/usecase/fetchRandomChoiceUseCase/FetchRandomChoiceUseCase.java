package com.adldoost.caracallanguage.usecase.fetchRandomChoiceUseCase;

import com.adldoost.caracallanguage.model.Word;
import com.adldoost.caracallanguage.model.WordSource;
import com.adldoost.caracallanguage.repository.WordSourceRepository;
import com.adldoost.caracallanguage.usecase.UseCase;
import com.adldoost.caracallanguage.usecase.fetchRandomWord.dto.FetchRandomWordUseCaseRequest;
import com.adldoost.caracallanguage.usecase.fetchRandomWord.dto.FetchRandomWordUseCaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class FetchRandomChoiceUseCase implements UseCase<FetchRandomChoiceRequest, FetchRandomChoiceResponse> {

    private final WordSourceRepository wordSourceRepository;
    @Override
    public FetchRandomChoiceResponse execute(FetchRandomChoiceRequest request) {
        WordSource wordSource = wordSourceRepository.findOne(Example.of(new WordSource()))
                .orElseThrow(() -> new RuntimeException("Word Source Not Found"));
        int size = wordSource.getWords().size();
        Random random = new Random();
        Word word = wordSource.getWords().get(random.nextInt(size-1));

        Set<String> choiceSet = new HashSet<>();
        choiceSet.add(word.getDestinationLanguageMeaning());

        while (choiceSet.size() < request.getChoiceCount()) {
            choiceSet.add(wordSource.getWords().get(random.nextInt(size - 1)).getDestinationLanguageMeaning());
        }
        return new FetchRandomChoiceResponse().setChoices(choiceSet);
    }
}
