package com.drivemart.repository;

import com.drivemart.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    // Дополнительные методы для поиска по критериям можно добавить здесь
}

