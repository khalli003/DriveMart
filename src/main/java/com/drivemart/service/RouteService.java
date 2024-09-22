package com.drivemart.service;

import com.drivemart.controller.RouteController;
import com.drivemart.model.Route;
import com.drivemart.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;


    private static final Logger logger = LoggerFactory.getLogger(RouteController.class);

    public Route findByOrderId(String orderId) {
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
    public List<Route> findAllRoutes() {
        return routeRepository.findAll();
    }

    public boolean deleteRoute(Long id) {
        if (routeRepository.existsById(id)) {
            routeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
