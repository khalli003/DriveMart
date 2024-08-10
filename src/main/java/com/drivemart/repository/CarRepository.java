package com.drivemart.repository;

import com.drivemart.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    // Дополнительные методы для поиска по критериям можно добавить здесь
}
