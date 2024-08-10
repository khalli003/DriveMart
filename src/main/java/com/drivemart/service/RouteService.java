package com.drivemart.service;

import com.drivemart.model.Route;
import com.drivemart.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public Route findByOrderId(Long orderId) {
        return routeRepository.findByOrderId(orderId);
    }

    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }

    public Route updateRoute(Long id, Route route) {
        if (routeRepository.existsById(id)) {
            route.setId(id);
            return routeRepository.save(route);
        }
        return null;
    }

    public boolean deleteRoute(Long id) {
        if (routeRepository.existsById(id)) {
            routeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
