package io.reflectoring.account_management_service.controller;

import io.reflectoring.account_management_service.constant.TransactionStatus;
import io.reflectoring.account_management_service.model.Transaction;
import io.reflectoring.account_management_service.service.AccountManagementService;
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
public class AccountManagementController {

  @Autowired
  private AccountManagementService accountManagementService;

  @PostMapping("/account")
  public ResponseEntity<Transaction> manage(@RequestBody Transaction transaction) {
    log.info("Process transaction with details: {}", transaction);
    Transaction processed = accountManagementService.manage(transaction);
    if (processed.getStatus().equals(TransactionStatus.SUCCESS)) {
      return ResponseEntity.ok(processed);
    } else {
      return ResponseEntity.internalServerError().body(processed);
    }
  }
}
