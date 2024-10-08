package com.drivemart.service;

import com.drivemart.model.BalanceTransaction;
import com.drivemart.repository.BalanceTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BalanceTransactionService {

    @Autowired
    private BalanceTransactionRepository balanceTransactionRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private CourierService courierService;

    public BalanceTransaction findById(Long id) {
        return balanceTransactionRepository.findById(id).orElse(null);
    }

    public BalanceTransaction createTransaction(BalanceTransaction transaction) {
        // Handle balance changes based on transaction type
        if (transaction.getTransactionType() == BalanceTransaction.TransactionType.CREDIT) {
            creditAccount(transaction.getAccountId(), transaction.getAmount());
        } else if (transaction.getTransactionType() == BalanceTransaction.TransactionType.DEBIT) {
            debitAccount(transaction.getAccountId(), transaction.getAmount());
        }
        return balanceTransactionRepository.save(transaction);
    }

    public BalanceTransaction updateTransaction(Long id, BalanceTransaction transaction) {
        if (balanceTransactionRepository.existsById(id)) {
            transaction.setId(id);
            return balanceTransactionRepository.save(transaction);
        }
        return null;
    }

    public boolean deleteTransaction(Long id) {
        if (balanceTransactionRepository.existsById(id)) {
            balanceTransactionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void creditAccount(Long accountId, BigDecimal amount) {

        companyService.addBalance(accountId, amount);
    }

    private void debitAccount(Long accountId, BigDecimal amount) {

        companyService.subtractBalance(accountId, amount);
    }
}
