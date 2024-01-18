package com.adldoost.caracallanguage.repository;

import com.adldoost.caracallanguage.model.UserActivity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActivityRepository extends MongoRepository<UserActivity, String> {

}
