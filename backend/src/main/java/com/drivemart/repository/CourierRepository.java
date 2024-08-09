package com.drivemart.repository;

import com.drivemart.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {
    // Дополнительные методы для поиска по критериям можно добавить здесь
}
