package com.adldoost.caracallanguage.usecase.fetchUserWordSourceListUseCase;

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
public class UserWordSourceBriefDto implements Serializable {
    private String id;
    private String sourceTitle;
    private String sourceDescription;
}
