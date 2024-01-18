package com.adldoost.caracallanguage.repository;

import com.adldoost.caracallanguage.model.UserWordSource;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserWordSourceRepository extends MongoRepository<UserWordSource, String> {

    Optional<UserWordSource> findByWordSourceIdAndUser(String wordSourceId, String user);
    Optional<UserWordSource> findByIdAndUser(String id, String user);

    List<UserWordSource> findByUser(String user);

}
