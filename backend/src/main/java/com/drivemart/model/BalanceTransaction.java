package com.drivemart.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class BalanceTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;

    @Column(nullable = false)
    private String type; // Тип транзакции (например, "Пополнение", "Списание")

    @Column(name = "account_id")
    private Long accountId; // Идентификатор счета, если это необходимо

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType; // Тип транзакции (например, "Дебет", "Кредит")

   /* @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType Type; // Тип транзакции (например, "Пополнение", "Списание")*/

    @Transient
    private Long AccountId; // Уникальный идентификатор для учета баланса


    public enum TransactionType {
        DEBIT,
        CREDIT
    }

}

