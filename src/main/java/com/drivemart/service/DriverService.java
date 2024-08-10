package com.drivemart.service;

import com.drivemart.model.Driver;
import com.drivemart.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public Driver findById(Long id) {
        return driverRepository.findById(id).orElse(null);
    }

    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public Driver updateDriver(Long id, Driver driver) {
        if (driverRepository.existsById(id)) {
            driver.setId(id);
            return driverRepository.save(driver);
        }
        return null;
    }

    public boolean deleteDriver(Long id) {
        if (driverRepository.existsById(id)) {
            driverRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public boolean existsById(Long id) {
        return driverRepository.existsById(id);
    }

    public void addBalance(Long driverId, BigDecimal amount) {
        Driver driver = findById(driverId);
        if (driver != null) {
            driver.setBalance(driver.getBalance().add(amount));
            driverRepository.save(driver);
        }
    }

    public void subtractBalance(Long driverId, BigDecimal amount) {
        Driver driver = findById(driverId);
        if (driver != null) {
            driver.setBalance(driver.getBalance().subtract(amount));
            driverRepository.save(driver);
        }
    }
}
