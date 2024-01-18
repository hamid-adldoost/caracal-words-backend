package com.adldoost.caracallanguage.usecase.IncreaseUserFacilitiesUseCase;

import com.adldoost.caracallanguage.model.UserFacilityType;
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
public class IncreaseUserFacilitiesUseCaseRequestDto implements Serializable {

    private String userId;
    private UserFacilityType facilityType;
    private Integer increaseCount;

}
