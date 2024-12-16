package com.via.greeninstalators.service;

import com.via.greeninstalators.model.Order;
import com.via.greeninstalators.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getOrdersByCompanyCode(String companyCode) {
        return orderRepository.findByCompanyCode(companyCode);
    }


    public Optional<Order> updateOrderStatus(UUID orderId, String status, String companyCode) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent() && optionalOrder.get().getCompanyCode().equals(companyCode)) {
            Order order = optionalOrder.get();
            order.setStatus(status);
            orderRepository.save(order);
            return Optional.of(order);
        }

        return Optional.empty();
    }
}
