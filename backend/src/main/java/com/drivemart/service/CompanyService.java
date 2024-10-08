package com.drivemart.service;

import com.drivemart.model.Car;
import com.drivemart.model.Company;
import com.drivemart.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company findById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(Long id, Company company) {
        if (companyRepository.existsById(id)) {
            company.setId(id);
            return companyRepository.save(company);
        }
        return null;
    }

    public boolean deleteCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Set<Car> getCompanyCars(Long id) {
        Company company = findById(id);
        return company != null ? company.getCars() : null;
    }

    public boolean existsById(Long id) {
        return companyRepository.existsById(id);
    }
    public void addBalance(Long companyId, BigDecimal amount) {
        Company company = findById(companyId);
        if (company != null) {
            BigDecimal newBalance = company.getBalance().add(amount);
            company.setBalance(newBalance);
            companyRepository.save(company);
        }
    }

    public void subtractBalance(Long companyId, BigDecimal amount) {
        Company company = findById(companyId);
        if (company != null) {
            BigDecimal newBalance = company.getBalance().add(amount);
            company.setBalance(newBalance);
            companyRepository.save(company);
        }
    }
}
