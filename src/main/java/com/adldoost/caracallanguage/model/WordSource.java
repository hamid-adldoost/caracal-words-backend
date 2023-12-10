package com.adldoost.caracallanguage.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "Word_Bag")
public class WordSource implements Serializable {

    @Id
    private String id;
    private String title;
    private String sourceLanguage;
    private String destinationLanguage;
    private String description;
    private List<Word> words;
    private Integer version;

}
