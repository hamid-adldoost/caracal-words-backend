package com.adldoost.caracallanguage.repository;

import com.adldoost.caracallanguage.model.WordSourceVersionInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WordSourceVersionInfoRepository extends MongoRepository<WordSourceVersionInfo, String> {

}
