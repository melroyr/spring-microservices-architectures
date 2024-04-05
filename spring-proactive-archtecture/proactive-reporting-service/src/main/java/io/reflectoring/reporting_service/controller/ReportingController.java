package io.reflectoring.reporting_service.controller;

import io.reflectoring.reporting_service.constant.TransactionStatus;
import io.reflectoring.reporting_service.model.Transaction;
import io.reflectoring.reporting_service.service.ReportingService;
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
public class ReportingController {

  @Autowired
  private ReportingService reportingService;

  @PostMapping("/report")
  public ResponseEntity<Transaction> report(@RequestBody Transaction transaction) {
    log.info("Process transaction with details: {}", transaction);
    Transaction processed = reportingService.report(transaction);
    if (processed.getStatus().equals(TransactionStatus.VALID)) {
      return ResponseEntity.ok(processed);
    } else {
      return ResponseEntity.internalServerError().body(processed);
    }
  }
}
