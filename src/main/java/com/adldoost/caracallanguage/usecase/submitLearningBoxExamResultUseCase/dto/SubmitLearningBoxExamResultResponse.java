package com.adldoost.caracallanguage.usecase.submitLearningBoxExamResultUseCase.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubmitLearningBoxExamResultResponse implements Serializable {

    private int learningBoxSize;
}
