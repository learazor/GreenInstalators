package com.via.greeninstalators.controller;

import com.via.greeninstalators.model.Order;
import com.via.greeninstalators.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/company-orders")
    public ResponseEntity<?> getCompanyOrders(HttpSession session) {

        String companyCode = (String) session.getAttribute("companyCode");

        if (companyCode == null) {
            return ResponseEntity.status(401).body("You must be logged in as a company to perform this action.");
        }

        // Fetch orders by companyCode
        List<Order> orders = orderService.getOrdersByCompanyCode(companyCode);

        return ResponseEntity.ok(orders);
    }

    @PostMapping("/accept")
    public ResponseEntity<?> acceptOrder(@RequestParam UUID orderId, HttpSession session) {
        // Retrieve the companyCode from session
        String companyCode = (String) session.getAttribute("companyCode");

        if (companyCode == null) {
            return ResponseEntity.status(401).body("You must be logged in as a company to perform this action.");
        }

        // Attempt to accept the order
        Optional<Order> updatedOrder = orderService.updateOrderStatus(orderId, "ACCEPTED", companyCode);

        if (updatedOrder.isEmpty()) {
            return ResponseEntity.status(403).body("Order does not belong to your company or does not exist.");
        }

        return ResponseEntity.ok(Map.of("message", "Order accepted successfully", "order", updatedOrder.get()));
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancelOrder(@RequestParam UUID orderId, HttpSession session) {
        // Retrieve the companyCode from session
        String companyCode = (String) session.getAttribute("companyCode");

        if (companyCode == null) {
            return ResponseEntity.status(401).body("You must be logged in as a company to perform this action.");
        }

        // Attempt to cancel the order
        Optional<Order> updatedOrder = orderService.updateOrderStatus(orderId, "CANCELED", companyCode);

        if (updatedOrder.isEmpty()) {
            return ResponseEntity.status(403).body("Order does not belong to your company or does not exist.");
        }

        return ResponseEntity.ok(Map.of("message", "Order canceled successfully", "order", updatedOrder.get()));
    }
}
