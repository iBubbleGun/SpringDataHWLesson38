package edu.hillel.springdatahwlesson38.repository;

import edu.hillel.springdatahwlesson38.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
