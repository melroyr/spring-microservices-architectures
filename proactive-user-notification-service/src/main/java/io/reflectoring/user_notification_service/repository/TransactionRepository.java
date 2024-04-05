package io.reflectoring.user_notification_service.repository;

import io.reflectoring.user_notification_service.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
