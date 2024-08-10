package com.drivemart.repository;

import com.drivemart.model.BalanceTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceTransactionRepository extends JpaRepository<BalanceTransaction, Long> {
    // Дополнительные методы для поиска по критериям можно добавить здесь
}

