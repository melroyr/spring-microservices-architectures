package io.reflectoring.account_management_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.reflectoring.account_management_service.constant.TransactionStatus;
import io.reflectoring.account_management_service.model.Transaction;
import io.reflectoring.account_management_service.model.User;
import io.reflectoring.account_management_service.repository.TransactionRepository;
import io.reflectoring.account_management_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountManagementService {

  @Autowired
  private TransactionRepository transactionRepo;

  @Autowired
  private UserRepository userRepo;

  public Transaction manage(Transaction transaction) {
    if (transaction.getStatus().equals(TransactionStatus.VALID)) {
      transaction.setStatus(TransactionStatus.SUCCESS);
      transactionRepo.save(transaction);

      User user = userRepo.findByCardId(transaction.getCardId());
      List<Transaction> validTransactions = user.getValidTransactions();
      if (validTransactions == null) {
    	  validTransactions = new ArrayList<>();
    	  validTransactions.add(transaction);
      }
      user.setValidTransactions(validTransactions);
      userRepo.save(user);
    }
    return transaction;
  }
}
