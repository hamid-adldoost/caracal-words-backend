package com.adldoost.caracallanguage.convert;

import com.adldoost.caracallanguage.model.User;
import com.adldoost.caracallanguage.model.Word;
import com.adldoost.caracallanguage.model.WordSource;
import com.adldoost.caracallanguage.repository.UserRepository;
import com.adldoost.caracallanguage.repository.WordSourceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConverterService {

    private final WordSourceRepository wordSourceRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

//    @PostConstruct
//    public void convert() throws IOException {
//
//        File file = new File(
//                this.getClass().getClassLoader().getResource("test-dict.json").getFile()
//        );
//
//        ObjectMapper mapper = new ObjectMapper();
//        RawModel[] words = mapper.readValue(file, RawModel[].class);
//
//        WordSource wordSource = new WordSource();
//        wordSource.setId(UUID.randomUUID().toString());
//        wordSource.setTitle("AI Selected Words");
//        wordSource.setVersion(1);
//        wordSource.setDescription("400 common words in english selected by AI");
//        wordSource.setDestinationLanguage("Persian");
//        wordSource.setSourceLanguage("English");
//        List<Word> wordList = new ArrayList<>();
//        for (RawModel word : words) {
//            Word w = new Word();
//            w.setId(UUID.randomUUID().toString());
//            w.setLevel(0);
//            w.setOpposite(List.of(""));
//            w.setExamples(word.getExample());
//            w.setSynonym(List.of(""));
//            w.setPronunciation(word.getPronunciation());
//            w.setPrepositionalPhrase(List.of(""));
//            w.setOriginalWord(word.getWord());
//            w.setSourceLanguageMeaning(word.getEnglishMeaning());
//            w.setDestinationLanguageMeaning(word.getPersian());
//            wordList.add(w);
//        }
//        wordSource.setWords(wordList);
//        wordSourceRepository.save(wordSource);
//    }

//    @PostConstruct
//    public void saveTestUser() {
//        User user = new User();
//        user.setId(UUID.randomUUID().toString());
//        user.setUsername("user");
//        user.setEmail("user@gmail.com");
//        user.setMobile("09143471899");
//        user.setFirstName("User");
//        user.setLastName("UserLastName");
//        user.setNationalId("2740032498");
//        user.setPassword(passwordEncoder.encode("userpass"));
//        user.setSignupDate(LocalDateTime.now());
//        userRepository.save(user);
//    }

}
