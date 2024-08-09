package com.drivemart.service;

import com.drivemart.model.BalanceTransaction;
import com.drivemart.repository.BalanceTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (transaction.getType() == TransactionType.CREDIT) {
            creditAccount(transaction.getAccountId(), transaction.getAmount());
        } else if (transaction.getType() == TransactionType.DEBIT) {
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

    private void creditAccount(Long accountId, Double amount) {
        // Implement credit logic here, based on the account type
        // For simplicity, assume accountId is a Company ID for this example
        companyService.addBalance(accountId, amount);
    }

    private void debitAccount(Long accountId, Double amount) {
        // Implement debit logic here, based on the account type
        // For simplicity, assume accountId is a Company ID for this example
        companyService.subtractBalance(accountId, amount);
    }
}

