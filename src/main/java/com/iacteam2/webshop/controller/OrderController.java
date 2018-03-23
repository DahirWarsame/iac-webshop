package com.iacteam2.webshop.controller;

/**
 * Created by dahir on Thu 22-03-18.
 */

import com.iacteam2.webshop.exception.ResourceNotFoundException;
import com.iacteam2.webshop.model.Order;
import com.iacteam2.webshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/rest/order")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;


    // Get All Notes
    @GetMapping("")
    public List<Order> getAllOrders() {
        System.out.println(orderRepository.findAll());
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable(value = "id") Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
    }

    @PostMapping("/new")
    public Order createOrder(@Valid @RequestBody Order order){
        return orderRepository.save(order);
    }
}
