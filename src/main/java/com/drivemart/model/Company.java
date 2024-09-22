
package com.drivemart.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String registrationNumber;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Car> cars = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Driver> drivers = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Courier> couriers = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders = new HashSet<>();

    @Column(nullable = false)
    private String address;

    // Дополнительные поля по необходимости

    public void addCar(Car car) {
        if (car != null && !cars.contains(car)) {
            cars.add(car);
            car.setCompany(this);
        } else {
            throw new IllegalArgumentException("Car is null or already added.");
        }
    }

    public void removeCar(Car car) {
        if (car != null && cars.contains(car)) {
            cars.remove(car);
            car.setCompany(null);
        } else {
            throw new IllegalArgumentException("Car is null or not found in the company.");
        }
    }

    public void addDriver(Driver driver) {
        if (driver != null && !drivers.contains(driver)) {
            drivers.add(driver);
            driver.setCompany(this);
        } else {
            throw new IllegalArgumentException("Driver is null or already added.");
        }
    }

    public void removeDriver(Driver driver) {
        if (driver != null && drivers.contains(driver)) {
            drivers.remove(driver);
            driver.setCompany(null);
        } else {
            throw new IllegalArgumentException("Driver is null or not found in the company.");
        }
    }

    public void addCourier(Courier courier) {
        if (courier != null && !couriers.contains(courier)) {
            couriers.add(courier);
            courier.setCompany(this);
        } else {
            throw new IllegalArgumentException("Courier is null or already added.");
        }
    }

    public void removeCourier(Courier courier) {
        if (courier != null && couriers.contains(courier)) {
            couriers.remove(courier);
            courier.setCompany(null);
        } else {
            throw new IllegalArgumentException("Courier is null or not found in the company.");
        }
    }

    public void addOrder(Order order) {
        if (order != null && !orders.contains(order)) {
            orders.add(order);
            order.setCompany(this);
        } else {
            throw new IllegalArgumentException("Order is null or already added.");
        }
    }

    public void removeOrder(Order order) {
        if (order != null && orders.contains(order)) {
            orders.remove(order);
            order.setCompany(null);
        } else {
            throw new IllegalArgumentException("Order is null or not found in the company.");
        }
    }

    public void updateBalance(BigDecimal amount) {
        if (amount != null) {
            this.balance = this.balance.add(amount);
        } else {
            throw new IllegalArgumentException("Amount cannot be null.");
        }
    }
}
