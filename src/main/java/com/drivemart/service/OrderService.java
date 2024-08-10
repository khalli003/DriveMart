package com.drivemart.service;

import com.drivemart.model.Order;
import com.drivemart.model.Route;
import com.drivemart.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RouteService routeService;

    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order order) {
        if (orderRepository.existsById(id)) {
            order.setId(id);
            return orderRepository.save(order);
        }
        return null;
    }

    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Route getOrderRoute(Long orderId) {
        return routeService.findByOrderId(orderId);
    }

    public void assignOrderToDriver(Long orderId, Long driverId) {
        Order order = findById(orderId);
        if (order != null) {
            order.setDriverId(driverId);
            updateOrder(orderId, order);
        }
    }
}
