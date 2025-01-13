package dev.pawel.orderservice.domain.service.order;

import dev.pawel.orderservice.controller.dto.SaveOrderRequest;
import dev.pawel.orderservice.domain.ProductQuantity;
import dev.pawel.orderservice.domain.SaveOrderResponse;
import dev.pawel.orderservice.domain.order.model.Order;
import dev.pawel.orderservice.domain.product.model.Product;
import dev.pawel.orderservice.infrastucture.repository.order.OrderRepository;
import dev.pawel.orderservice.infrastucture.repository.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Clock;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final Clock clock;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, Clock clock, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.clock = clock;
        this.productRepository = productRepository;
    }

    public Order findOrderById(String orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public Order create(SaveOrderRequest saveOrderRequest) {
        System.out.println(saveOrderRequest);
        String orderNumber = UUID.randomUUID().toString();
        List<ProductQuantity> products = saveOrderRequest.products().stream()
                .map(p -> {
                    Product product = productRepository.findById(p.id()).orElseThrow();
                    return new ProductQuantity(product, p.quantity());
                })
                .toList();
        BigDecimal totalCost = products.stream()
                .map(productQuantity -> productQuantity
                        .getProduct()
                        .getPrice()
                        .multiply(new BigDecimal(productQuantity.getQuantity()))
                )
                .reduce(BigDecimal::add)
                .orElseThrow();
        Order createOrder = new Order(clock.instant(), orderNumber, totalCost, products);
        System.out.println(createOrder);
        return orderRepository.save(createOrder);
    }
}
