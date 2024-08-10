package com.drivemart.repository;

import com.drivemart.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    // Дополнительные методы для поиска по критериям можно добавить здесь
}
