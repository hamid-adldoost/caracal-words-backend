package com.adldoost.caracallanguage.repository;

import com.adldoost.caracallanguage.model.WordSource;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WordSourceRepository extends MongoRepository<WordSource, String> {



}
