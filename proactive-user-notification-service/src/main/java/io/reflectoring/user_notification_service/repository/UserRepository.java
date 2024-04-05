package io.reflectoring.user_notification_service.repository;

import io.reflectoring.user_notification_service.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByCardId(String cardId);
}
