package io.reflectoring.reporting_service.repository;

import io.reflectoring.reporting_service.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByCardId(String cardId);
}
