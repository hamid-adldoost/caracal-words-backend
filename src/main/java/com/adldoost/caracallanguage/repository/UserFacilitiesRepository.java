package com.adldoost.caracallanguage.repository;

import com.adldoost.caracallanguage.model.UserFacilities;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserFacilitiesRepository extends MongoRepository<UserFacilities, String> {

    Optional<UserFacilities> findByUserId(String userId);

}
