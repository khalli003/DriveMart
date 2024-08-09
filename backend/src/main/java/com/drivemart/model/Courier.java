package com.drivemart.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@Entity
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String region; // Регион, в котором работает курьер

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public void setCompany(Company company) {
        this.company = company;
    }
}
