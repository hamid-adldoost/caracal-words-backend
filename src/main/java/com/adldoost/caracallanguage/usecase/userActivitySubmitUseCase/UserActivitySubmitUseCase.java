package com.adldoost.caracallanguage.usecase.userActivitySubmitUseCase;

import com.adldoost.caracallanguage.model.UserActivity;
import com.adldoost.caracallanguage.repository.UserActivityRepository;
import com.adldoost.caracallanguage.usecase.UseCase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@JsonIgnoreProperties
public class UserActivitySubmitUseCase implements UseCase<UserActivitySubmitUseCaseRequestDto, UserActivitySubmitUseCaseResponseDto> {

    private UserActivityRepository repository;
    @Override
    public UserActivitySubmitUseCaseResponseDto execute(UserActivitySubmitUseCaseRequestDto request) {

        UserActivity userActivity = new UserActivity();
        userActivity.setActivityDate(LocalDateTime.now());
        userActivity.setActivityType(request.getActivityType());
        userActivity.setUserId(request.getUserId());
        userActivity.setDescription(request.getDescription());
        repository.save(userActivity);
        return new UserActivitySubmitUseCaseResponseDto();
    }
}
