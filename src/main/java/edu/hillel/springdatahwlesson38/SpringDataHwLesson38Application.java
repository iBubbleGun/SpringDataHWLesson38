package edu.hillel.springdatahwlesson38;

import edu.hillel.springdatahwlesson38.model.Order;
import edu.hillel.springdatahwlesson38.model.Product;
import edu.hillel.springdatahwlesson38.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class SpringDataHwLesson38Application implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(SpringDataHwLesson38Application.class);

    private final OrderService orderService;

    @Autowired
    public SpringDataHwLesson38Application(OrderService orderService) {
        this.orderService = orderService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataHwLesson38Application.class, args);
    }

    @Override
    public void run(String... args) {
        if (addExampleInitialDataInToDatabase()) {
            LOG.info(String.valueOf(orderService.getAllOrders().getBody()));
        }
    }

    private boolean addExampleInitialDataInToDatabase() {
        final List<Product> products1 = List.of(
                new Product(1L, "Product 1", 11.11),
                new Product(2L, "Product 2", 22.22),
                new Product(3L, "Product 3", 33.33),
                new Product(4L, "Product 4", 44.44),
                new Product(5L, "Product 5", 55.55));

        final List<Product> products2 = List.of(
                new Product(6L, "Product 6", 66.66),
                new Product(7L, "Product 7", 77.77),
                new Product(8L, "Product 8", 88.88),
                new Product(9L, "Product 9", 99.99),
                new Product(10L, "Product 10", 100.10));

        final Order order1 = new Order(1L, products1);
        final Order order2 = new Order(2L, products2);

        orderService.addOrder(order1);
        orderService.addOrder(order2);

        if (!Objects.requireNonNull(orderService.getAllOrders().getBody()).isEmpty()) {
            LOG.info("Example initial data successfully added in to database.");
            return true;
        } else {
            LOG.error("An error occurred when trying to add example initial data in to database!");
            return false;
        }
    }
}
