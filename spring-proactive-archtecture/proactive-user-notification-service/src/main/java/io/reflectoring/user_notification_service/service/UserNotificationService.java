package io.reflectoring.user_notification_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import io.reflectoring.user_notification_service.constant.TransactionStatus;
import io.reflectoring.user_notification_service.model.Transaction;
import io.reflectoring.user_notification_service.model.User;
import io.reflectoring.user_notification_service.repository.TransactionRepository;
import io.reflectoring.user_notification_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserNotificationService {

  @Autowired
  private TransactionRepository transactionRepo;

  @Autowired
  private UserRepository userRepo;

  @Autowired
  private JavaMailSender emailSender;

  public Transaction notify(Transaction transaction) {

    if (transaction.getStatus().equals(TransactionStatus.FRAUDULENT)) {

      User user = userRepo.findByCardId(transaction.getCardId());

      // Notify user by sending email
      SimpleMailMessage message = new SimpleMailMessage();
      message.setFrom("noreply@baeldung.com");
      message.setTo(user.getEmail());
      message.setSubject("Fraudulent transaction attempt from your card");
      message.setText("An attempt has been made to pay " + transaction.getStoreName()
              + " from card " + transaction.getCardId() + " in the country "
              + transaction.getTransactionLocation() + "." +
              " Please report to your bank or block your card.");
      //emailSender.send(message);
      transaction.setStatus(TransactionStatus.FRAUDULENT_NOTIFY_SUCCESS);
    } else {
      transaction.setStatus(TransactionStatus.FRAUDULENT_NOTIFY_FAILURE);
    }
    return transactionRepo.save(transaction);
  }
}
