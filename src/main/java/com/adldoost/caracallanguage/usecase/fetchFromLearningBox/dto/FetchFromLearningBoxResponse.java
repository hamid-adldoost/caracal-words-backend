package com.adldoost.caracallanguage.usecase.fetchFromLearningBox.dto;

import com.adldoost.caracallanguage.usecase.fetchRandomWord.dto.FetchRandomWordUseCaseResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FetchFromLearningBoxResponse extends FetchRandomWordUseCaseResponse {

    private int learningBoxSize;
    private int score;

}
