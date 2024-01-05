package edu.hillel.springdatahwlesson38.controller;

import edu.hillel.springdatahwlesson38.exception.OrderIdNotFoundException;
import edu.hillel.springdatahwlesson38.model.Order;
import edu.hillel.springdatahwlesson38.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/get/{orderId}")
    public Order getOrder(@PathVariable("orderId") Long id) throws OrderIdNotFoundException {
        return orderService.getOrder(id).getBody();
    }

    @GetMapping("/get")
    public List<Order> getOrder() {
        return orderService.getAllOrders().getBody();
    }

    @PostMapping("/add")
    public String addOrder(@RequestBody Order order) {
        return orderService.addOrder(order).getBody();
    }

    @DeleteMapping("/delete/{orderId}")
    public String deleteOrder(@PathVariable("orderId") Long id) {
        return orderService.deleteOrder(id).getBody();
    }
}
