package com.adldoost.caracallanguage.usecase.addUserWordSourceUseCase;

import com.adldoost.caracallanguage.model.UserWord;
import com.adldoost.caracallanguage.model.UserWordSource;
import com.adldoost.caracallanguage.model.WordSource;
import com.adldoost.caracallanguage.repository.UserWordSourceRepository;
import com.adldoost.caracallanguage.repository.WordSourceRepository;
import com.adldoost.caracallanguage.usecase.UseCase;
import com.adldoost.caracallanguage.usecase.addUserWordSourceUseCase.dto.AddUserWordSourceUseCaseRequest;
import com.adldoost.caracallanguage.usecase.addUserWordSourceUseCase.dto.AddUserWordSourceUseCaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddUserWordSourceUseCaseService
        implements UseCase<AddUserWordSourceUseCaseRequest,
        AddUserWordSourceUseCaseResponse> {

    private final WordSourceRepository wordSourceRepository;
    private final UserWordSourceRepository userWordSourceRepository;

    @Override
    public AddUserWordSourceUseCaseResponse execute(AddUserWordSourceUseCaseRequest request) {

        request.setUser(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        //check word Source is valid
        //check if user has already added the word source
        //add convert wordSource to userWordSource
        //save UserWordSource
        //return UserWordSource id


        WordSource wordSource = wordSourceRepository.findById(request.getWordSourceId())
                .orElseThrow(() -> new RuntimeException("WordSourceNotFound"));

        String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        userWordSourceRepository.findByWordSourceIdAndUser(request.getWordSourceId(), user).ifPresent(ws -> {
                    throw new RuntimeException("Word Source Already Exists for User");
                }
        );
        UserWordSource userWordSource = new UserWordSource();
        userWordSource.setUser(user);
        userWordSource.setWordSourceId(wordSource.getId());
        userWordSource.setSourceDescription(wordSource.getDescription());
        userWordSource.setId(UUID.randomUUID().toString());
        userWordSource.setCreationDate(LocalDateTime.now());
        List<UserWord> userWords = new ArrayList<>();
        wordSource.getWords().forEach(w -> {
            UserWord userWord = new UserWord();
            userWord.setWord(w);
            userWord.setScore(0);
            userWord.setReviewResultMap(new HashMap<>());
            userWords.add(userWord);
        });
        userWordSource.setUserWords(userWords);
        userWordSource = userWordSourceRepository.save(userWordSource);
        return new AddUserWordSourceUseCaseResponse().setUserWordSourceId(userWordSource.getId());
    }
}
