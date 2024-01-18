package com.adldoost.caracallanguage.usecase.IncreaseUserFacilitiesUseCase;

import com.adldoost.caracallanguage.model.UserFacilities;
import com.adldoost.caracallanguage.repository.UserFacilitiesRepository;
import com.adldoost.caracallanguage.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class IncreaseUserFacilitiesUseCase implements UseCase<IncreaseUserFacilitiesUseCaseRequestDto, IncreaseUserFacilitiesUseCaseResponseDto> {

    private final UserFacilitiesRepository userFacilitiesRepository;
    @Override
    public IncreaseUserFacilitiesUseCaseResponseDto execute(IncreaseUserFacilitiesUseCaseRequestDto request) {
        UserFacilities userFacilities = userFacilitiesRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new RuntimeException("user facilities not found"));
        Integer currentCount = userFacilities.getFacilities().get(request.getFacilityType());
        userFacilities.getFacilities().put(request.getFacilityType(), currentCount + request.getIncreaseCount());
        userFacilities = userFacilitiesRepository.save(userFacilities);
        return new IncreaseUserFacilitiesUseCaseResponseDto()
                .setFacilityResultCount(userFacilities.getFacilities().get(request.getFacilityType()));

    }
}
