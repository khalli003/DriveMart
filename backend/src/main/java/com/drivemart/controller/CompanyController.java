package com.drivemart.controller;

import com.drivemart.model.Car;
import com.drivemart.model.Company;
import com.drivemart.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER')")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.findById(id);
        return company != null ? ResponseEntity.ok(company) : ResponseEntity.notFound().build();
    }


    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        Company createdCompany = companyService.createCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCompany);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        Company updatedCompany = companyService.updateCompany(id, company);
        return updatedCompany != null ? ResponseEntity.ok(updatedCompany) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        boolean deleted = companyService.deleteCompany(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/cars")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER')")
    public ResponseEntity<Set<Car>> getCompanyCars(@PathVariable Long id) {
        Set<Car> cars = companyService.getCompanyCars(id);
        return cars != null ? ResponseEntity.ok(cars) : ResponseEntity.notFound().build();
    }
}

