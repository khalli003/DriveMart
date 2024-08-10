package com.drivemart.repository;

import com.drivemart.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    Route findByOrderId(Long orderId);


}

