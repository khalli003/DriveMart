package com.drivemart.controller;

import com.drivemart.model.Route;
import com.drivemart.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping("/{orderId}")
    public ResponseEntity<Route> getRouteByOrderId(@PathVariable String orderId) {
        Route route = routeService.findByOrderId(orderId);
        return route != null ? ResponseEntity.ok(route) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes() {
        List<Route> routes = routeService.findAllRoutes();
        return ResponseEntity.ok(routes);
    }

    @PostMapping
    public ResponseEntity<Route> createRoute(@RequestBody Route route) {
        if (route.getPoints() == null || route.getPoints().isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Валидация
        }
        Route createdRoute = routeService.createRoute(route);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoute);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Route> updateRoute(@PathVariable Long id, @RequestBody Route route) {
        if (route.getPoints() == null || route.getPoints().isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Валидация
        }
        Route updatedRoute = routeService.updateRoute(id, route);
        return updatedRoute != null ? ResponseEntity.ok(updatedRoute) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        boolean deleted = routeService.deleteRoute(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
