package dev.pawel.orderservice.domain.service.order;

import dev.pawel.orderservice.domain.order.model.Order;
import dev.pawel.orderservice.infrastucture.repository.order.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
import java.util.random.RandomGenerator;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
//    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
//        this.productRepository = productRepository;
    }

    public Order findOrderById(String orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }

    public Order create() {
        Order createOrder = new Order("1", LocalDate.now(), "", 0L, BigDecimal.ZERO);
        return orderRepository.save(createOrder);
    }

    public Order addProduct(Order order) {
        return null;
    }

    public Order deleteProductFromOrderByProductId(String productId) {
        return null;
    }
}
