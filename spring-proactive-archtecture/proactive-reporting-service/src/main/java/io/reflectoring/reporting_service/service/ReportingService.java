package io.reflectoring.reporting_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.reflectoring.reporting_service.constant.TransactionStatus;
import io.reflectoring.reporting_service.model.Transaction;
import io.reflectoring.reporting_service.model.User;
import io.reflectoring.reporting_service.repository.TransactionRepository;
import io.reflectoring.reporting_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReportingService {

  @Autowired
  private TransactionRepository transactionRepo;

  @Autowired
  private UserRepository userRepo;

  public Transaction report(Transaction transaction) {

    if (transaction.getStatus().equals(TransactionStatus.FRAUDULENT_NOTIFY_SUCCESS)
        || transaction.getStatus().equals(
            TransactionStatus.FRAUDULENT_NOTIFY_FAILURE)) {

      // Report the User's account and take automatic action against
      // User's account or card
      User user = userRepo.findByCardId(transaction.getCardId());
      user.setFraudulentActivityAttemptCount(
          user.getFraudulentActivityAttemptCount() + 1);
      user.setAccountLocked(user.getFraudulentActivityAttemptCount() > 3);
      user.getFraudulentTransactions().add(transaction);
      userRepo.save(user);

      transaction.setStatus(user.isAccountLocked()
                            ? TransactionStatus.ACCOUNT_BLOCKED
                            : TransactionStatus.FAILURE);
    }
    return transactionRepo.save(transaction);
  }
}