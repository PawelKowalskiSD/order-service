package dev.pawel.orderservice.infrastucture.repository.order;

import dev.pawel.orderservice.domain.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}
