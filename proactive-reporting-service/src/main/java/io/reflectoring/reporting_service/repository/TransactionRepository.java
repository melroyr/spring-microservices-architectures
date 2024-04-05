package io.reflectoring.reporting_service.repository;

import io.reflectoring.reporting_service.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
