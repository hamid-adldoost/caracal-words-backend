package com.adldoost.caracallanguage.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Word implements Serializable {

    @Id
    private String id;
    private String originalWord;
    private String sourceLanguageMeaning;
    private String destinationLanguageMeaning;
    private String examples;
    private String pronunciation;
    private Integer level;
    private List<String> opposite;
    private List<String> synonym;
    private List<String> prepositionalPhrase;
}
