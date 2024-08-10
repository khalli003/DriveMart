package com.drivemart.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String orderId;

    @ElementCollection
    @CollectionTable(name = "route_points", joinColumns = @JoinColumn(name = "route_id"))
    @Column(name = "point")
    private List<String> points; // Список точек маршрута в формате "latitude,longitude"
}

