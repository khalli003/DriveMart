
package com.drivemart.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.math.BigDecimal;
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
    private BigDecimal balance;

    @OneToMany(mappedBy = "company")
    private Set<Car> cars;

    @OneToMany(mappedBy = "company")
    private Set<Driver> drivers;

    @OneToMany(mappedBy = "company")
    private Set<Courier> couriers;

    @OneToMany(mappedBy = "company")
    private Set<Order> orders;

    @Column(nullable = false)
    private String address;

    // Additional fields as needed

    public void addCar(Car car) {
        cars.add(car);
        car.setCompany(this);
    }

    public void removeCar(Car car) {
        cars.remove(car);
        car.setCompany(null);
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
        driver.setCompany(this);
    }

    public void removeDriver(Driver driver) {
        drivers.remove(driver);
        driver.setCompany(null);
    }

    public void addCourier(Courier courier) {
        couriers.add(courier);
        courier.setCompany(this);
    }

    public void removeCourier(Courier courier) {
        couriers.remove(courier);
        courier.setCompany(null);
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setCompany(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setCompany(null);
    }

    public void updateBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }
}
