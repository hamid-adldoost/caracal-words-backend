package com.adldoost.caracallanguage.usecase.userActivitySubmitUseCase;

import com.adldoost.caracallanguage.model.UserActivityType;
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
public class UserActivitySubmitUseCaseRequestDto implements Serializable {
    private String userId;
    private UserActivityType activityType;
    private String description;
}
