package com.adldoost.caracallanguage.usecase.addUserWordSourceUseCase.dto;

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
public class AddUserWordSourceUseCaseRequest implements Serializable {

    private String wordSourceId;
    private String user;
}
