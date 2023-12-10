package com.adldoost.caracallanguage.convert;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RawModel implements Serializable {

    private String word;
    private Integer score;
    private String pronunciation;
    private String englishMeaning;
    private String persian;
    private String example;

}
