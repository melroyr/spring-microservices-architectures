package io.reflectoring.user_notification_service.controller;

import io.reflectoring.user_notification_service.constant.TransactionStatus;
import io.reflectoring.user_notification_service.model.Transaction;
import io.reflectoring.user_notification_service.service.UserNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/banking")
public class UserNotificationController {

  @Autowired
  private UserNotificationService userNotificationService;

  @PostMapping("/fraud")
  public ResponseEntity<Transaction> notify(@RequestBody Transaction transaction) {
    log.info("Process transaction with details and notify user: {}", transaction);
    Transaction processed = userNotificationService.notify(transaction);
    if (processed.getStatus().equals(TransactionStatus.SUCCESS)) {
      return ResponseEntity.ok(processed);
    } else {
      return ResponseEntity.internalServerError().body(processed);
    }
  }
}