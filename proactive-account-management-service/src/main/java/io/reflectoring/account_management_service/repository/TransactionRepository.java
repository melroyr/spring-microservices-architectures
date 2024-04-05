package io.reflectoring.account_management_service.repository;

import io.reflectoring.account_management_service.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
