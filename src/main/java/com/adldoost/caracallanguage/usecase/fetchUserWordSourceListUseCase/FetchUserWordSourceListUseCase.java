package com.adldoost.caracallanguage.usecase.fetchUserWordSourceListUseCase;

import com.adldoost.caracallanguage.model.UserWordSource;
import com.adldoost.caracallanguage.repository.UserWordSourceRepository;
import com.adldoost.caracallanguage.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FetchUserWordSourceListUseCase implements UseCase<FetchUserWordSourceListUseCaseRequest, FetchUserWordSourceListUseCaseResponse> {

    private final UserWordSourceRepository repository;

    @Override
    public FetchUserWordSourceListUseCaseResponse execute(FetchUserWordSourceListUseCaseRequest request) {
        List<UserWordSource> userWordSourceList = repository.findByUser(request.getUser());
        if(CollectionUtils.isEmpty(userWordSourceList)) {
            return new FetchUserWordSourceListUseCaseResponse().setUserWordSourceList(new ArrayList<>());
        }
        FetchUserWordSourceListUseCaseResponse response = new FetchUserWordSourceListUseCaseResponse()
                .setUserWordSourceList(new ArrayList<>());
        userWordSourceList.forEach(u -> {
            response.getUserWordSourceList().add(new UserWordSourceBriefDto()
                    .setId(u.getId())
                    .setSourceTitle(u.getSourceTitle())
                    .setSourceDescription(u.getSourceDescription()));
        });
        return response;
    }
}
