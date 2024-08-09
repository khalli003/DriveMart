package com.drivemart.controller;

import com.drivemart.model.Courier;
import com.drivemart.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/couriers")
public class CourierController {

    @Autowired
    private CourierService courierService;

    @GetMapping("/{id}")
    public ResponseEntity<Courier> getCourierById(@PathVariable Long id) {
        Courier courier = courierService.findById(id);
        return courier != null ? ResponseEntity.ok(courier) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Courier> createCourier(@RequestBody Courier courier) {
        Courier createdCourier = courierService.createCourier(courier);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourier);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Courier> updateCourier(@PathVariable Long id, @RequestBody Courier courier) {
        Courier updatedCourier = courierService.updateCourier(id, courier);
        return updatedCourier != null ? ResponseEntity.ok(updatedCourier) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourier(@PathVariable Long id) {
        boolean deleted = courierService.deleteCourier(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
