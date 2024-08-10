package com.drivemart.service;

import com.drivemart.model.Courier;
import com.drivemart.repository.CourierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CourierService {

    @Autowired
    private CourierRepository courierRepository;

    public Courier findById(Long id) {
        return courierRepository.findById(id).orElse(null);
    }

    public Courier createCourier(Courier courier) {
        return courierRepository.save(courier);
    }

    public Courier updateCourier(Long id, Courier courier) {
        if (courierRepository.existsById(id)) {
            courier.setId(id);
            return courierRepository.save(courier);
        }
        return null;
    }

    public boolean deleteCourier(Long id) {
        if (courierRepository.existsById(id)) {
            courierRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public boolean existsById(Long id) {
        return courierRepository.existsById(id);
    }

    public void addBalance(Long courierId, BigDecimal amount) {
        Courier courier = findById(courierId);
        if (courier != null) {
            BigDecimal newBalance = courier.getBalance().add(amount);
            courier.setBalance(newBalance);
            courierRepository.save(courier);
        }
    }

    public void subtractBalance(Long courierId, BigDecimal amount) {
        Courier courier = findById(courierId);
        if (courier != null) {
            BigDecimal newBalance = courier.getBalance().subtract(amount);
            courier.setBalance(newBalance);
            courierRepository.save(courier);
        }
    }
}
