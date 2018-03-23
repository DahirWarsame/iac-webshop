package com.iacteam2.webshop.controller;

/**
 * Created by dahir on Thu 22-03-18.
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iacteam2.generated.Address;
import com.iacteam2.webshop.exception.ResourceNotFoundException;
import com.iacteam2.webshop.model.Order;
import com.iacteam2.webshop.repository.CustomerRepository;
import com.iacteam2.webshop.repository.OrderRepository;
import com.iacteam2.webshop.repository.ProductRepository;
import com.iacteam2.webshop.soap.PaymentsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/rest/order")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;


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
    @ResponseBody
    public String createOrder(@Valid @RequestBody String json) throws JsonProcessingException {
        Order order = new Order();

        PaymentsClient pc = new PaymentsClient();
        Address ad = new Address();
        try {
            int code = pc.makePayment("Timo", ad, 10.1F);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return json;
    }
}
