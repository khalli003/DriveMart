package com.drivemart.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String category; // Эконом, комфорт, бизнес, минивэн, т.д.

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime completedAt;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;

    @Column
    private String status; // Статус заказа (например, "Новый", "Выполнен")

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public void setDriverId(Long driverId) {
        this.driver.setId(driverId);
    }
}
