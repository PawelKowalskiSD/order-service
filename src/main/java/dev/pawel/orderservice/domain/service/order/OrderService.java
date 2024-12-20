package dev.pawel.orderservice.domain.service.order;

import dev.pawel.orderservice.domain.order.model.Order;
import dev.pawel.orderservice.infrastucture.repository.order.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order findOrderById(String orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }

    public Order create(Order order) {
        return orderRepository.save(order);
    }
}
