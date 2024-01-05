package edu.hillel.springdatahwlesson38.repository;

import edu.hillel.springdatahwlesson38.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
