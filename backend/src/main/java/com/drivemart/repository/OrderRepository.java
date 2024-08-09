package com.drivemart.repository;

import com.drivemart.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Дополнительные методы для поиска по критериям можно добавить здесь
}

