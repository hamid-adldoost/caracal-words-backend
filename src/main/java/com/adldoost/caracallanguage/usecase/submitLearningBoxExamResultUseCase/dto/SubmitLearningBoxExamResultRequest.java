package com.adldoost.caracallanguage.usecase.submitLearningBoxExamResultUseCase.dto;

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
@JsonIgnoreProperties
public class SubmitLearningBoxExamResultRequest implements Serializable {

    private String userWordSourceId;
    private String wordId;
    private Boolean result;
}
