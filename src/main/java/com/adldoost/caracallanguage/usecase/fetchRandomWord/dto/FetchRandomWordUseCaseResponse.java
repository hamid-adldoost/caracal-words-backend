package com.adldoost.caracallanguage.usecase.fetchRandomWord.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FetchRandomWordUseCaseResponse implements Serializable {

    private String sourceId;
    private String wordId;
    private String originalWord;
    private String sourceLanguageMeaning;
    private String destinationLanguageMeaning;
    private String examples;
    private String pronunciation;
    private List<String> choices;

}
