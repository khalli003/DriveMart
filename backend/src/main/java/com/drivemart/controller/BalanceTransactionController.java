package com.drivemart.controller;

import com.drivemart.model.BalanceTransaction;
import com.drivemart.service.BalanceTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance-transactions")
public class BalanceTransactionController {

    @Autowired
    private BalanceTransactionService balanceTransactionService;

    @GetMapping("/{id}")
    public ResponseEntity<BalanceTransaction> getTransactionById(@PathVariable Long id) {
        BalanceTransaction transaction = balanceTransactionService.findById(id);
        return transaction != null ? ResponseEntity.ok(transaction) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BalanceTransaction> createTransaction(@RequestBody BalanceTransaction transaction) {
        BalanceTransaction createdTransaction = balanceTransactionService.createTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTransaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BalanceTransaction> updateTransaction(@PathVariable Long id, @RequestBody BalanceTransaction transaction) {
        BalanceTransaction updatedTransaction = balanceTransactionService.updateTransaction(id, transaction);
        return updatedTransaction != null ? ResponseEntity.ok(updatedTransaction) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        boolean deleted = balanceTransactionService.deleteTransaction(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
