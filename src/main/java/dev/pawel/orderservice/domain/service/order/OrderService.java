package dev.pawel.orderservice.domain.service.order;

import dev.pawel.orderservice.domain.order.model.Order;
import dev.pawel.orderservice.infrastucture.repository.order.OrderRepository;
import dev.pawel.orderservice.infrastucture.repository.product.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order findOrderById(String orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }

    public Order create(Order order) {
        return orderRepository.save(order);
    }

    public Order addProduct(Order order) {
        return null;
    }

    public Order deleteProductFromOrderByProductId(String productId) {
        return null;
    }
}
