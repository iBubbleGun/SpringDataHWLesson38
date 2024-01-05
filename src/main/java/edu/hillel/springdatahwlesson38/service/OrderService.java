package edu.hillel.springdatahwlesson38.service;

import edu.hillel.springdatahwlesson38.exception.OrderIdNotFoundException;
import edu.hillel.springdatahwlesson38.model.Order;
import edu.hillel.springdatahwlesson38.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ResponseEntity<Order> getOrder(Long id) throws OrderIdNotFoundException {
        LOG.info("Call getOrder() method with id={} parameter.", id);
        return ResponseEntity.status(HttpStatus.OK).body(
                orderRepository.findById(id).orElseThrow(() -> new OrderIdNotFoundException(
                        "Order with id=" + id + " not found!"))
        );
    }

    public ResponseEntity<List<Order>> getAllOrders() {
        LOG.info("Call getAllOrders() method.");
        return ResponseEntity.status(HttpStatus.OK).body(
                orderRepository.findAll(Sort.by("id"))
        );
    }

    public ResponseEntity<String> addOrder(Order order) {
        LOG.info("Call addOrder() method with order={} parameter.", order);

        orderRepository.save(order);

        if (orderRepository.existsById(order.getId())) {
            LOG.info("New order " + order + " successfully added.");
            return ResponseEntity.status(HttpStatus.OK).body("New order " + order + " successfully added.");
        } else {
            LOG.error("Error occurred while trying to add order: " + order);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error. Please, try again.");
        }
    }

    public ResponseEntity<String> deleteOrder(Long id) {
        LOG.info("Call deleteOrder() method with id={} parameter.", id);
        orderRepository.deleteById(id);
        if (orderRepository.findById(id).isEmpty()) {
            LOG.info("Order with id=" + id + " successfully deleted.");
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Order with id=" + id + " successfully deleted.");
        } else {
            LOG.error("Error occurred while trying to delete order with id=" + id + ".");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error occurred while trying to delete order with id=" + id + ".");
        }
    }
}
