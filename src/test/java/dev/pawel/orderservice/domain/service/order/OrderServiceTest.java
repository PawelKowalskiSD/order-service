package dev.pawel.orderservice.domain.service.order;

import dev.pawel.orderservice.domain.ProductQuantity;
import dev.pawel.orderservice.domain.order.model.Order;
import dev.pawel.orderservice.domain.product.model.Product;
import dev.pawel.orderservice.infrastucture.repository.order.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private Clock clock;

    @Test
    void shouldFindOrderById() {
        //Given
        List<ProductQuantity> products = new ArrayList<>();
        products.add(new ProductQuantity(new Product("1", "bag", new BigDecimal("12")), 2));
        Order order1 = new Order("1", clock.instant(), "111230", new BigDecimal("24"), products);
        when(orderRepository.findById(order1.getId())).thenReturn(Optional.of(order1)).thenThrow();
        //When
        Order result = orderService.findOrderById(order1.getId());
        //Then
        verify(orderRepository, times(1)).findById(result.getId());
        verify(clock, times(1)).instant();
    }
}