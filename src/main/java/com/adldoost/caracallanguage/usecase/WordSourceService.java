package com.adldoost.caracallanguage.usecase;

import com.adldoost.caracallanguage.repository.WordSourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WordSourceService {

    private final WordSourceRepository wordSourceRepository;


}
